name := "spark-essentials"

version := "0.1"

scalaVersion := "2.12.10"

val sparkVersion = "3.0.1"
val vegasVersion = "0.3.11"
val postgresVersion = "42.2.2"

resolvers ++= Seq(
  "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven",
  "Typesafe Simple Repository" at "https://repo.typesafe.com/typesafe/simple/maven-releases",
  "MavenRepository" at "https://mvnrepository.com"
)


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  // logging
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.codehaus.groovy" % "groovy" % "3.0.7",
  // postgres for DB connectivity
  "org.postgresql" % "postgresql" % postgresVersion
).map( _.exclude( "org.slf4j", "slf4j-log4j12" ) )