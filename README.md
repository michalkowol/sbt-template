# Scala templates

* [finatra](https://github.com/michalkowol/sbt-template/tree/master/finatra)
* [play](https://github.com/michalkowol/sbt-template/tree/master/play)
* [sbt](https://github.com/michalkowol/sbt-template/tree/master/sbt)
* [spray](https://github.com/michalkowol/sbt-template/tree/master/spray)

## Code Snippets

### MDC Propagating Execution Context

`mdc.scala`:
```scala
package com.michalkowol.spray

import java.util.concurrent.TimeUnit

import akka.dispatch._
import com.typesafe.config.Config
import org.slf4j.MDC

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.{Duration, FiniteDuration}

trait MDCPropagatingExecutionContext extends ExecutionContext {
  self =>

  override def prepare(): ExecutionContext = new ExecutionContext {
    val mdcContext = MDC.getCopyOfContextMap

    def execute(callee: Runnable): Unit = self.execute(new Runnable {
      def run(): Unit = {
        val oldMDCContext = MDC.getCopyOfContextMap

        setContextMap(mdcContext)
        try {
          callee.run()
        } finally {
          setContextMap(oldMDCContext)
        }
      }
    })

    private[this] def setContextMap(context: java.util.Map[String, String]): Unit = {
      if (context == null) { // scalastyle:ignore
        MDC.clear()
      } else {
        MDC.setContextMap(context)
      }
    }

    def reportFailure(t: Throwable) = self.reportFailure(t)
  }
}

class MDCPropagatingDispatcherConfigurator(config: Config, prerequisites: DispatcherPrerequisites)
    extends MessageDispatcherConfigurator(config, prerequisites) {

  override val dispatcher: MessageDispatcher = new MDCPropagatingDispatcher(
    this,
    config.getString("id"),
    config.getInt("throughput"),
    FiniteDuration(config.getDuration("throughput-deadline-time", TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS),
    configureExecutor(),
    FiniteDuration(config.getDuration("shutdown-timeout", TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
  )
}

private class MDCPropagatingDispatcher(
  configurator: MessageDispatcherConfigurator,
  id: String,
  throughput: Int,
  throughputDeadlineTime: Duration,
  executorServiceFactoryProvider: ExecutorServiceFactoryProvider,
  shutdownTimeout: FiniteDuration
) extends Dispatcher(configurator, id, throughput, throughputDeadlineTime, executorServiceFactoryProvider, shutdownTimeout)
    with MDCPropagatingExecutionContext

object MDCPropagatingExecutionContext {

  object Implicits {
    implicit lazy val global = MDCPropagatingExecutionContextWrapper(ExecutionContext.Implicits.global)
  }

}

class MDCPropagatingExecutionContextWrapper(wrapped: ExecutionContext) extends ExecutionContext with MDCPropagatingExecutionContext {
  override def execute(r: Runnable): Unit = wrapped.execute(r)

  override def reportFailure(t: Throwable): Unit = wrapped.reportFailure(t)
}

object MDCPropagatingExecutionContextWrapper {
  def apply(wrapped: ExecutionContext): MDCPropagatingExecutionContextWrapper = {
    new MDCPropagatingExecutionContextWrapper(wrapped)
  }
}
```

`application.conf`:
```
akka.actor.default-dispatcher.type = com.mtvi.spray.MDCPropagatingDispatcherConfigurator
```