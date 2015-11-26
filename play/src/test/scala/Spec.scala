import org.scalatest.mock.MockitoSugar
import org.scalatest.{OptionValues, Matchers, FlatSpec}
import org.scalatestplus.play.{OneAppPerTest, OneBrowserPerSuite, OneServerPerSuite}

trait Spec extends FlatSpec with Matchers with OptionValues with MockitoSugar
