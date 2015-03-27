name := "project"
scalaVersion in ThisBuild := "2.11.6"

incOptions := incOptions.value.withNameHashing(nameHashing = true)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

jacoco.settings

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"
libraryDependencies += "com.paypal" %% "cascade-common" % "0.4.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19" % "test"
