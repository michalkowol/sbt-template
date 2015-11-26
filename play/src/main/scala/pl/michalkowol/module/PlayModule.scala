package pl.michalkowol.module

import pl.michalkowol.play.{Filters, ErrorHandler}
import play.api.routing.Router
import scaldi.Module

class PlayModule extends Module {
  bind[ErrorHandler] to injected[ErrorHandler] ('router -> injectProvider[Router])
  bind[Filters] to injected[Filters]
}
