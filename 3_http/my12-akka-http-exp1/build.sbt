name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.12.6"

lazy val akkaVersion = "2.5.13"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",

  "com.typesafe.akka" %% "akka-http"   % "10.1.2",
	"com.typesafe.akka" %% "akka-stream" % "2.5.12" // or whatever the latest version is
)
