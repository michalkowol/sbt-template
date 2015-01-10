resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("org.scala-sbt.plugins" % "sbt-onejar" % "0.8")
addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.6")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.4.0")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.6")
addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")
