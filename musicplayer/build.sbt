//import Dependencies._
//
//lazy val root = (project in file(".")).
//  settings(
//    inThisBuild(List(
//      organization := "com.example",
//      scalaVersion := "2.12.4",
////      scalaVersion := "2.11.2",
//      version      := "0.1.0-SNAPSHOT"
//    )),
//    name := "MusicPlayer",
//    libraryDependencies ++= Seq(
//      scalaTest % Test,
//      "com.typesafe.akka" %% "akka-actor" % "2.5.9",
////      "com.typesafe.akka" %% "akka-actor" % "2.3.11"
//)
//  )

name := "Playing with Actors"

version := "1.0"

scalaVersion := "2.11.2"

sbtVersion := "0.13.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.11")