package pl.michalkowol

import akka.actor.ActorSystem
import spray.routing._

object Boot extends SimpleRoutingApp with Api {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("api-system")
    startServer(interface = "0.0.0.0", port = 8080) {
      route
    }
  }
}

trait Api extends HttpService with FooRoute {
  val route: Route = pathPrefix("api") {
    foo
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
