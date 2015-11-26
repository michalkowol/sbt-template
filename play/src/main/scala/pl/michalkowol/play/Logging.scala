package pl.michalkowol.play

import play.api.Logger

trait Logging {
  val log = Logger(this.getClass)
}
