name := """play-java-hello-world-tutorial"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.1"

libraryDependencies += guice

enablePlugins(PlayEbean)
libraryDependencies += evolutions
libraryDependencies += jdbc
libraryDependencies += "com.h2database" % "h2" % "1.4.200"
libraryDependencies ++= Seq(ehcache)
mainClass in assembly := Some("play.core.server.ProdServerStart")
fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
}
