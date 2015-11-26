package pl.michalkowol.play

import javax.inject.Provider

import play.api._
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._
import play.api.routing.Router

import scala.concurrent._

class ErrorHandler(env: Environment, config: Configuration, sourceMapper: OptionalSourceMapper, router: () => Router)
    extends DefaultHttpErrorHandler(env, config, sourceMapper, new Provider[Router] { def get(): Router = router() }) {

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    exception match {
      case ex: IllegalArgumentException => Future.successful(PaymentRequired(ex.toString))
      case _ => super.onServerError(request, exception)
    }
  }

  override def onForbidden(request: RequestHeader, message: String): Future[Result] = Future.successful {
    Forbidden("You're not allowed to access this resource.")
  }
}