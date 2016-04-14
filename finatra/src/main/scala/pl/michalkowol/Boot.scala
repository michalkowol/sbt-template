package pl.michalkowol

import com.google.inject.Module
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.logging.modules.Slf4jBridgeModule

case class HiRequest(id: Long, name: String)

class HelloWorldController extends Controller {

  get("/api/foo/:name") { request: Request =>
    val name = request.params.getOrElse("name", "")
    s"foo $name"
  }

  get("/api/bar/:a/:b") { request: Request =>
    info("foo")
    val a = request.params.getIntOrElse("a", 0)
    val b = request.params.getIntOrElse("b", 0)
    s"$a + $b = ${a + b}"
  }

  post("/api/hi") { hiRequest: HiRequest =>
    "Hello " + hiRequest.name + " with id " + hiRequest.id
  }
}

object HelloWorldServerMain extends HelloWorldServer

class HelloWorldServer extends HttpServer {

  override def modules: Seq[Module] = Seq(Slf4jBridgeModule)

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[HelloWorldController]
  }
}
