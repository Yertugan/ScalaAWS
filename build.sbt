name := "scala"

version := "0.1"

scalaVersion := "2.12.8"


lazy val akkaVersion = "2.5.25"
lazy val elastic4sVersion = "6.2.10"

lazy val slickVersion = "3.2.1"
lazy val mysqlVersion = "5.1.34"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http"   % "10.1.10",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.10",
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.sksamuel.elastic4s" %% "elastic4s-core" % elastic4sVersion,
  "com.sksamuel.elastic4s" %% "elastic4s-http" % elastic4sVersion,
  "com.sksamuel.elastic4s" %% "elastic4s-json4s" % elastic4sVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,

  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "mysql" % "mysql-connector-java" % mysqlVersion,

  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.531"
)
