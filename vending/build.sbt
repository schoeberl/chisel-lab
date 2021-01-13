
scalaVersion := "2.12.12"

scalacOptions := Seq("-deprecation", "-Xsource:2.11")

// Suppress "there were X feature warnings; re-run with -feature for details".
// These appear because chisel use a language feature that's not available in
// all scala implementations.
scalacOptions += "-language:reflectiveCalls"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

// Chisel 3.4
libraryDependencies += "edu.berkeley.cs" %% "chisel-iotesters" % "1.5.1"
libraryDependencies += "edu.berkeley.cs" %% "chiseltest" % "0.3.1"


libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"
