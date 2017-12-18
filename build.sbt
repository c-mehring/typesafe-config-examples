name := "typesafe-config-examples"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "com.typesafe" % "config" % "1.3.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

logBuffered in Test := false