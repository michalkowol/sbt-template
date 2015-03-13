resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"
def plugin(m: ModuleID) = Defaults.sbtPluginExtra(m, "0.13", "2.10") excludeAll ExclusionRule("org.scala-lang")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")
addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.6")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.8")

libraryDependencies += plugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0" excludeAll ExclusionRule("org.scalariform"))
libraryDependencies += "com.danieltrinh" %% "scalariform" % "0.1.5"
