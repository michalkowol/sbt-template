import scalariform.formatter.preferences._

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignArguments, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(FormatXml, false)
  .setPreference(PreserveSpaceBeforeArguments, true)
  .setPreference(SpacesAroundMultiImports, false)
  .setPreference(SpacesWithinPatternBinders, false)
