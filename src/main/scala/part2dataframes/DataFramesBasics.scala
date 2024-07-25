package part2dataframes

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.types._
import org.apache.spark.sql.{ Row, SparkSession }

object DataFramesBasics extends App with LazyLogging {
  
  // Creating a Spark Session
  val spark = SparkSession.builder
      .appName( "DataFrame Basics" )
      .config( "spark.master", "local" )
      .getOrCreate()
  logger.info( "SparkSession variable created" )
  
  // reading a DataFrame
  logger.info( "Creating a DataFrame from a source JSON file ( cars.json ) with infered schema ..." )
  val firstDf = spark.read
      .format( "json" )
      .option( "inferSchema", "true" )
      .load( "src/main/resources/data/cars.json" )
  
  // showing a DataFrame
  logger.info( "Showing created DataFrame contents ..." )
  firstDf.show( truncate = false )
  logger.info( "Showing the schema of the created DataFrame ..." )
  firstDf.printSchema()
  
  logger.info( "Taking the first 10 rows of the DataFrame. This results in an `Array[Row]`" )
  val first10Rows = firstDf.take( 10 )
  
  logger.info( "Inspecting the first row, we can identify theat the concrete implementation of the `Row` trait is `GenericRowWithSchema`" )
  val firstRow: Row = first10Rows( 0 )
  logger.debug( s"Row's implementation class name: ${firstRow.getClass.getSimpleName}" )
  first10Rows.foreach( println )
  
  // Spark Types
  val longType = LongType
  
  // Schema
  val carsSchema = StructType(
    Array(
      StructField( "Name", StringType ),
      StructField( "Miles_per_Gallon", DoubleType ),
      StructField( "Cylinders", LongType ),
      StructField( "Displacement", DoubleType ),
      StructField( "Horsepower", LongType ),
      StructField( "Weight_in_lbs", LongType ),
      StructField( "Acceleration", DoubleType ),
      StructField( "Year", StringType ),
      StructField( "Origin", StringType )
    )
  )
  
  // Obtaining a schema of an existing DataFrame
  val carsDfSchema = firstDf.schema
  println( carsDfSchema )
  
  // Read a DataFrame with your own schema
  logger.info( "Creating a DataFrame using cars.json as source, and explicity providing the cars schema ..." )
  val carsDfWithSchema = spark.read
      .format( "json" )
      .schema( carsSchema )
      .load( "src/main/resources/data/cars.json" )
  
  carsDfWithSchema.show( false )
  
  // Create a single row by hand
  val myRow = Row( "chevrolet chevelle malibu", 18.0, 8L, 307.0, 130L, 3504L, 12.0, "1970-01-01", "USA" )
  
  // Create a DataFrame from a Sequence of tuples
  val cars = Seq(
    ("chevrolet chevelle malibu", 18.0, 8L, 307.0, 130L, 3504L, 12.0, "1970-01-01", "USA"),
    ("buick skylark 320", 15.0, 8L, 350.0, 165L, 3693L, 11.5, "1970-01-01", "USA"),
    ("plymouth satellite", 18.0, 8L, 318.0, 150L, 3436L, 11.0, "1970-01-01", "USA"),
    ("amc rebel sst", 16.0, 8L, 304.0, 150L, 3433L, 12.0, "1970-01-01", "USA"),
    ("ford torino", 17.0, 8L, 302.0, 140L, 3449L, 10.5, "1970-01-01", "USA"),
    ("ford galaxie 500", 15.0, 8L, 429.0, 198L, 4341L, 10.0, "1970-01-01", "USA"),
    ("chevrolet impala", 14.0, 8L, 454.0, 220L, 4354L, 9.0, "1970-01-01", "USA"),
    ("plymouth fury iii", 14.0, 8L, 440.0, 215L, 4312L, 8.5, "1970-01-01", "USA"),
    ("pontiac catalina", 14.0, 8L, 455.0, 225L, 4425L, 10.0, "1970-01-01", "USA"),
    ("amc ambassador dpl", 15.0, 8L, 390.0, 190L, 3850L, 8.5, "1970-01-01", "USA")
  )
  
  logger.info( "Creating a DataFrame from a Sequence of tuples ..." )
  logger.info( "Note: By using the `createDataFrame( Seq[Tuple] )` method, the schema is automatically inferred" )
  val manualCarsDF = spark.createDataFrame( cars ) // schema is automatically inferred
  manualCarsDF.show( false )
  logger.info( "Showing the schema that was automatically inferred ..." )
  manualCarsDF.printSchema()
  // Note: DataFrames have schemas. Rows, do not.
  
  // Create DataFrames with implicits
  logger.info( "Creating a DataFrame using implicits ..." )
  import spark.implicits._
  
  logger.info( "We use the previously-defined Seq[Tuple] called `cars`." )
  logger.info( "Since we imported spark.implicits._ such Seq of Tuple now can \"magically\" call a toDF function and " +
      "provide the column names of the automatically-inferred DataFrame schema." )
  val manualCarsWithImplicits = cars.toDF( "Name", "MPG", "Cylinders", "Displacement", "HP", "Weight",
    "Acceleration", "Year", "CountryOrigin"
  )
  
  manualCarsWithImplicits.show( false )
  manualCarsWithImplicits.printSchema
}
