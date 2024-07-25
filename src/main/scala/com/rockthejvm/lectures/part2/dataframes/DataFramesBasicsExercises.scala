package com.rockthejvm.lectures.part2.dataframes

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession

object DataFramesBasicsExercises extends App with LazyLogging {
  /**
   * Exercises:
   */
  
  /**
   * 1) Create manual DataFrame describing smartphones
   *  - make
   *  - model
   *  - screen dimension
   *  - camera megapixels
   */
  val spark = SparkSession.builder
      .master( "local" )
      .getOrCreate()
  
  logger.info( "1) Create manual DataFrame describing smartphones" )
  import spark.implicits._
  val smartphones = Seq(
    ("Apple", "iPhone 11 Pro Max", "6.5\"", "12 MP"),
    ("Samsung", "Galaxy S21 Ultra 5G", "6.8\"", "108 MP"),
    ("Sony", "Xperia 5 II", "6.1\"", "12 MP"),
    ("Xiaomi", "Mi 11", "6.81\"", "108 MP"),
  )
  
  val smartphonesDF = smartphones.toDF( "Make", "Model", "Screen Dimension", "Camera Megapixels" )
  smartphonesDF.show( false )
  smartphonesDF.printSchema()
  
  /**
   * 2) Read another file from the data/ folder, e.g. movies.json
   *  - print its schema
   *  - count the number of rows, call count
   */
  logger.info( "2) Read another file from the data/ folder, e.g. movies.json" )
  
  val moviesDf = spark.read
      .format( "json" )
      .option( "inferSchema", "true" )
      .load( "src/main/resources/data/movies.json" )
  
  moviesDf.show( false )
  moviesDf.printSchema()
  logger.info( s"The number of movies in this Dataframe is: ${moviesDf.count()} movie(s)." )
}
