import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "my17-hello-akka-pr1",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.typesafe.akka" %% "akka-actor" % "2.5.13",
      "com.typesafe.akka" %% "akka-persistence" % "2.4.17",
      "org.iq80.leveldb" % "leveldb" % "0.7",

      "com.typesafe.akka" %% "akka-remote" % "2.4.17"
    )
  )
