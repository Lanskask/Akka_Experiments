import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.11.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "my12-akka-streams-proj1",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.typesafe.akka" %% "akka-stream" % "2.5.13",
      "com.typesafe.akka" %% "akka-http" % "10.1.3",
      "org.twitter4j" % "twitter4j-stream" % "4.0.3",
      "com.typesafe.akka" % "akka-persistence_2.11" % "2.4.17",
      "org.iq80.leveldb" % "leveldb" % "0.7",
      "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.25.1"

      //      "com.lightbend.akka" % "akka-stream-alpakka-amqp_2.11" % "0.5"
    )
  )
