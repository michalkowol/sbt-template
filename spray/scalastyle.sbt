lazy val scalastyleCompile = taskKey[Unit]("Run scalastyle before compile")
scalastyleCompile := {
  (org.scalastyle.sbt.ScalastylePlugin.scalastyle in Compile).toTask("").value
}
(compile in Compile) <<= (compile in Compile) dependsOn scalastyleCompile