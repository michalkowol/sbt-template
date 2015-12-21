package pl.yourcode.photocomparer.web

import spray.http.{HttpHeader, AllOrigins}
import spray.http.HttpHeaders.{`Access-Control-Max-Age`, `Access-Control-Allow-Headers`, `Access-Control-Allow-Methods`, `Access-Control-Allow-Origin`}
import spray.http.HttpMethods._
import spray.routing._

trait CORSDirective extends HttpServiceBase {

  def corsFilter: Directive0 = {
    val rh = implicitly[RejectionHandler]
    addResponseHeaders(headersWithOrigin) & handleRejections(rh)
  }

  private def headersWithOrigin = List(
    `Access-Control-Allow-Origin`(AllOrigins),
    `Access-Control-Allow-Methods`(GET, POST, PUT, OPTIONS, DELETE, PATCH),
    `Access-Control-Allow-Headers`(
      "Origin",
      "X-Requested-With",
      "Content-Type",
      "Accept",
      "Accept-Encoding",
      "Accept-Language",
      "Host",
      "Referer",
      "User-Agent",
      "X-Processed-By",
      "Authorization"
    ),
    `Access-Control-Max-Age`(3600L * 24 * 365)
  )

  private def addResponseHeaders(myHeaders: List[HttpHeader]): Directive0 = respondWithHeaders(myHeaders)
}
