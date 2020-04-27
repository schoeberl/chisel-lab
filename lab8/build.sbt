scalaVersion := "2.11.12"

// Suppress "there were X feature warnings; re-run with -feature for details".
// These appear because chisel use a language feature that's not available in
// all scala implementations.
scalacOptions += "-language:reflectiveCalls"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.2.2"
libraryDependencies += "edu.berkeley.cs" %% "chisel-iotesters" % "1.3.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"
// chisel testers 2: 0.1.2
