import play.api.test.Helpers._
import play.api.test._
import scaldi.play.ScaldiApplicationBuilder._

class ApplicationSpec extends Spec {

  "Application" should "send 404 on a bad request" in {
    withScaldiApp() {
      status(route(FakeRequest(GET, "/foo")).value) shouldBe NOT_FOUND
    }
  }

  it should "render the index page" in {
    withScaldiApp() {
      val home = route(FakeRequest(GET, "/")).get

      status(home) shouldBe OK
      contentType(home).value shouldBe "text/plain"
      contentAsString(home) should include("Your new application is ready.")
    }
  }
}