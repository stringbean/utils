name := "utils"
organization := "software.purpledragon"

scalaVersion := "2.13.3"

crossScalaVersions := Seq("2.11.12", "2.12.12", scalaVersion.value)

// format: off
libraryDependencies ++= Seq(
  "org.slf4j"     %  "slf4j-api" % "1.7.30",
  "org.scalatest" %% "scalatest" % "3.2.0" % Test
)
// format: on

headerLicense := Some(HeaderLicense.ALv2("2020", "Michael Stringer"))
licenses += ("Apache-2.0", url("https://opensource.org/licenses/Apache-2.0"))
developers := List(
  Developer("stringbean", "Michael Stringer", "@the_stringbean", url("https://github.com/stringbean"))
)
organizationName := "Purple Dragon Software"
organizationHomepage := Some(url("https://purpledragon.software"))
homepage := Some(url("https://github.com/stringbean/utils"))
scmInfo := Some(
  ScmInfo(url("https://github.com/stringbean/utils"), "https://github.com/stringbean/utils.git"))

bintrayPackageLabels := Seq("utils")

releaseCrossBuild := true
releasePublishArtifactsAction := PgpKeys.publishSigned.value

import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  releaseStepTask(mimaFindBinaryIssues),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  setNextVersion,
  commitNextVersion,
  pushChanges
)

// no previous version (yet)
mimaFailOnNoPrevious := false