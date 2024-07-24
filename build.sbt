name := "spark-essentials-with-scala-rtjvm"
version := "0.1"
scalaVersion := "2.13.14"

val sparkVersion = "3.5.1"
val vegasVersion = "0.3.11"
val postgresVersion = "42.7.3"
val scalaLoggingVersion = "3.9.5"
val log4jApiScalaVersion = "13.1.0"

resolvers += "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven"
resolvers += "Typesafe Simple Repository" at "https://repo.typesafe.com/typesafe/simple/maven-releases"
resolvers += "MavenRepository" at "https://mvnrepository.com"


libraryDependencies ++= Seq(
  // Apache Spark
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  
  // Logging
  "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
  "org.apache.logging.log4j" %% "log4j-api-scala" % log4jApiScalaVersion,
  
  // PostgreSQL
  "org.postgresql" % "postgresql" % postgresVersion
)