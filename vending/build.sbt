scalaVersion := "2.11.12"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.2.2"
libraryDependencies += "edu.berkeley.cs" %% "chisel-iotesters" % "1.3.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"


// published tester 2 probably needs Scala 2.12
// libraryDependencies += "edu.berkeley.cs" %% "chiseltest" % "0.1.2"
