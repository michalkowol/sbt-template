package pl.michalkowol.controller

import pl.michalkowol.play.Logging
import play.api.mvc._

// scalastyle:off public.methods.have.type

class Application extends Controller with Logging {

  def index = Action {
    Ok("Your new application is ready.")
  }
}
