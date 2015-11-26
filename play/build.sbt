name := "play-scala"
version in ThisBuild := "1.0.0"
scalaVersion in ThisBuild := "2.11.7"

incOptions := incOptions.value.withNameHashing(nameHashing = true)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-encoding", "UTF-8", "-Ywarn-adapted-args:false")

jacoco.settings
jacoco.thresholds in jacoco.Config := de.johoop.jacoco4sbt.Thresholds(instruction = 75, method = 75, branch = 45, complexity = 55, line = 85, clazz = 85)

lazy val root = (project in file(".")).enablePlugins(PlayScala).disablePlugins(PlayLayoutPlugin)

libraryDependencies += jdbc
libraryDependencies += ws
libraryDependencies += filters

libraryDependencies += "org.scaldi" %% "scaldi-play" % "0.5.11"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"

libraryDependencies += "org.scalatestplus" %% "play" % "1.4.0-M4" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.5" % "test"
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19" % "test"

routesGenerator := InjectedRoutesGenerator
