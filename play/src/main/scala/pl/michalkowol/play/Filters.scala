package pl.michalkowol.play

import play.api.http.HttpFilters
import play.api.mvc.EssentialFilter
import play.filters.cors.CORSFilter

class Filters(corsFilter: CORSFilter) extends HttpFilters {
  override def filters: Seq[EssentialFilter] = Seq(corsFilter)
}