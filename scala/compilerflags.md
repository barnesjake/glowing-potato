## Compiler flags
https://docs.scala-lang.org/overviews/compiler-options/index.html
```
scalacOptions := Seq(
  // Feature options
  "-encoding", "utf-8",
  "-explaintypes",
  "-feature",
  "-language:existentials",
  "-language:experimental.macros",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Ymacro-annotations",

  // Warnings as errors!
  "-Xfatal-warnings",

  // Linting options
  "-unchecked",
  "-Xcheckinit",
  "-Xlint:adapted-args",
  "-Xlint:constant",
  "-Xlint:delayedinit-select",
  "-Xlint:deprecation",
  "-Xlint:doc-detached",
  "-Xlint:inaccessible",
  "-Xlint:infer-any",
  "-Xlint:missing-interpolator",
  "-Xlint:nullary-override",
  "-Xlint:nullary-unit",
  "-Xlint:option-implicit",
  "-Xlint:package-object-classes",
  "-Xlint:poly-implicit-overload",
  "-Xlint:private-shadow",
  "-Xlint:stars-align",
  "-Xlint:type-parameter-shadow",
  "-Wdead-code",
  "-Wextra-implicit",
  "-Wnumeric-widen",
  "-Wunused:implicits",
  "-Wunused:imports",
  "-Wunused:locals",
  "-Wunused:params",
  "-Wunused:patvars",
  "-Wunused:privates",
  "-Wvalue-discard",
)
```

## wonf (ignoring certain errors/warns, Scala 2.13)
```
scalacOptions ++= Seq(
  // "-Xfatal-warnings",
  "-Wconf:cat=deprecation:ws,any:e",
)
```

What this does is to turn all warnings into errors, except for deprecation messages, which are still left as warnings. To break it down:

-`cat=deprecation` refers to deprecation messages (classes/methods marked with `@deprecated` being called) and `:ws` says that for these warnings a “warning summary” should be shown

-`any:e` says that for any other kind of warning, signal it via an error
