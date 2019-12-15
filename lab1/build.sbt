
scalaVersion := "2.11.12"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.2.1"

// libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "latest.release"
