name := """ckafka-consumer-elasticsearch"""
organization := "ravi"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "7.9.2",
  "org.apache.kafka" % "kafka-clients" % "2.0.0")

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "ravi.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "ravi.binders._"
