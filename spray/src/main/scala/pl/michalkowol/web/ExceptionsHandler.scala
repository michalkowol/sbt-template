package pl.yourcode.photocomparer.web

import com.paypal.cascade.json.JsonUtil
import spray.http.{MediaTypes, HttpEntity, StatusCode}
import spray.routing._

import scala.util.control.NonFatal
import spray.http.StatusCodes._

object ExceptionsHandler {
  private case class Error(uri: String, method: String, code: Int, message: Option[String])
}

trait ExceptionsHandler extends HttpServiceBase {

  import ExceptionsHandler._

  def handleExceptionsFilter: Directive0 = handleExceptions(eh)

  private def eh: ExceptionHandler = ExceptionHandler {
    case NonFatal(ex) => ctx => createErrorResponse(ctx, InternalServerError, ex)
  }

  private def createErrorResponse(ctx: RequestContext, code: StatusCode, ex: Throwable): Unit = {
    val uri = ctx.request.uri.toString()
    val method = ctx.request.method.name
    val error = Error(uri, method, code.intValue, Option(ex.getMessage))
    val httpEntity = HttpEntity(MediaTypes.`application/json`, JsonUtil.toJson(error).get)
    ctx.complete(code, httpEntity)
  }
}
