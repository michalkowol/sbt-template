import scalariform.formatter.preferences._

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(AlignParameters, true)
  .setPreference(PreserveSpaceBeforeArguments, true)
  .setPreference(SpacesAroundMultiImports, false)
