package pl.michalkowol

import akka.actor.ActorSystem
import akka.actor.Actor
import spray.routing._
import akka.actor.Props
import akka.io.IO
import spray.can.Http

object Boot {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("api-system")
    val api = system.actorOf(Props[Api])
    val bindListener = system.actorOf(Props[BindListener])
    val bind = Http.Bind(api, "0.0.0.0", port = 9000)
    IO(Http)(system).tell(bind, bindListener)
  }
}

class BindListener extends Actor with Logging {
  override def receive: Receive = {
    case bound: Http.Bound =>
      log.info("Bound to {}", bound.localAddress)
      context.stop(self)
    case error =>
      log.error(s"$error")
      context.system.terminate()
  }
}

class Api extends HttpServiceActor with FooRoute with BarRoute {
  def receive: Receive = runRoute {
    pathPrefix("api") {
      foo ~ bar
    }
  }
}

trait FooRoute extends HttpService {
  val foo: Route = get {
    path("foo" / Segment) { name =>
      complete {
        s"foo $name"
      }
    }
  }
}

trait BarRoute extends HttpService {
  val bar: Route = get {
    path("bar" / IntNumber / IntNumber) { (a, b) =>
      complete {
        s"$a + $b = ${a + b}"
      }
    }
  }
}
