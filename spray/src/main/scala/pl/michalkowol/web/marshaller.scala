package pl.yourcode.photocomparer

import com.paypal.cascade.json.JsonUtil
import spray.http.MediaTypes._
import spray.http.{ContentType, ContentTypeRange, HttpEntity}
import spray.httpx.marshalling.Marshaller
import spray.httpx.unmarshalling.Unmarshaller

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

package object marshaller {

  private val supportedMediaTypes = Seq(`application/json`)
  private val supportedContentTypes = supportedMediaTypes.map { mediaType => ContentType(mediaType) }
  private val supportedContentTypeRange = supportedMediaTypes.map { mediaType => ContentTypeRange(mediaType) }

  private val jsonMarshaller = Marshaller.delegate[Any, String](`application/json`) { value => JsonUtil.toJson(value).get }

  implicit def futureMarshaller[T](implicit ec: ExecutionContext) = Marshaller[Future[T]] { (value, ctx) => // scalastyle:ignore
    value.onComplete {
      case Success(v) => marshaller(v, ctx)
      case Failure(error) => ctx.handleError(error)
    }
  }

  implicit def marshaller[T] = Marshaller[T] { (value, ctx) => // scalastyle:ignore
    ctx.tryAccept(supportedContentTypes) match {
      case Some(contentType) => jsonMarshaller(value, ctx)
      case _ => ctx.rejectMarshalling(supportedContentTypes)
    }
  }

  implicit def unmarshaller[T: Manifest]: Unmarshaller[T] = Unmarshaller[T](supportedContentTypeRange: _*) {
    case HttpEntity.NonEmpty(contentType, data) => JsonUtil.fromJson[T](data.asString).get
  }
}