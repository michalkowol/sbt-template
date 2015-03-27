name := "project"
scalaVersion in ThisBuild := "2.11.6"

incOptions := incOptions.value.withNameHashing(nameHashing = true)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

jacoco.settings
Revolver.settings
Revolver.enableDebugging()

val sprayVersion = "1.3.3"
val akkaVersion = "2.3.9"
val cascadeVersion = "0.4.6"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"
libraryDependencies += "com.paypal" %% "cascade-common" % cascadeVersion
libraryDependencies += "com.paypal" %% "cascade-akka" % cascadeVersion
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
libraryDependencies += "io.spray" %% "spray-routing" % sprayVersion
libraryDependencies += "io.spray" %% "spray-client" % sprayVersion

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19" % "test"
libraryDependencies += "io.spray" %% "spray-testkit" % sprayVersion % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-actor-tests" % akkaVersion % "test"
