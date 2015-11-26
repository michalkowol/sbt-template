resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype repository" at "https://oss.sonatype.org/content/repositories/releases/"

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.1")
addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.6")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.9")
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.5.1")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2")