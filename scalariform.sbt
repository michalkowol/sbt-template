import scalariform.formatter.preferences._

scalariformPreferences := scalariformPreferences.value
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(AlignParameters, true)
  .setPreference(PreserveSpaceBeforeArguments, true)
  .setPreference(SpacesAroundMultiImports, false)