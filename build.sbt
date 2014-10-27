name := "project"

version := "1.0"

scalaVersion := "2.11.2"

fork in run := true

incOptions := incOptions.value.withNameHashing(true)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

Seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

org.scalastyle.sbt.ScalastylePlugin.Settings

instrumentSettings

scalariformSettings

libraryDependencies ++= Seq(
  "com.google.guava" % "guava" % "18.0",
  "com.google.code.findbugs" % "jsr305" % "3.0.0",
  "org.scaldi" %% "scaldi" % "0.4",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "org.scalatest" %% "scalatest" % "2.2.2" % "test",
  "org.mockito" % "mockito-all" % "1.10.8" % "test"
)
