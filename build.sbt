import Dependencies._

scalaVersion := "2.13.0"

libraryDependencies += Dependencies.playJson
libraryDependencies += Dependencies.scalatest


lazy val `lib-play-json-assert` = (project in file("."))
  .settings(publish := {}, publishLocal := {})

publishArtifact in(Test, packageBin) in ThisBuild := true
