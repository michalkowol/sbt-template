assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "commons", "logging", xs @ _*) => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

test in assembly := {}
