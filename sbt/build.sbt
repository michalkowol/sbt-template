name := "sbt-template"
version in ThisBuild := "1.0.0"
scalaVersion in ThisBuild := "2.11.8"

incOptions := incOptions.value.withNameHashing(nameHashing = true)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-encoding", "UTF-8", "-Ywarn-adapted-args:false")

jacoco.settings
jacoco.thresholds in jacoco.Config := de.johoop.jacoco4sbt.Thresholds(instruction = 75, method = 75, branch = 45, complexity = 55, line = 85, clazz = 85)

Revolver.settings
Revolver.enableDebugging(port = 5005, suspend = false)

test in assembly := {}

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.7"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
libraryDependencies += "com.paypal" %% "cascade-common" % "0.5.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19" % "test"
