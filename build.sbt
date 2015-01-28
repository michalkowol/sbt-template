name := "project"
scalaVersion in ThisBuild := "2.11.5"

incOptions := incOptions.value.withNameHashing(true)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

com.github.retronym.SbtOneJar.oneJarSettings
jacoco.settings

libraryDependencies += "org.scaldi" %% "scaldi" % "0.4"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"
libraryDependencies += "com.paypal" %% "cascade-common" % "0.4.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.3" % "test"
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19" % "test"