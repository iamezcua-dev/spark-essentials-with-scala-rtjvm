package com.rockthejvm.lectures.part1.recap

import com.typesafe.scalalogging.LazyLogging

import java.util.concurrent.TimeUnit
import scala.annotation.{nowarn, unused}
import scala.concurrent.Future
import scala.language.implicitConversions
import scala.util.{Failure, Success}

object ScalaRecap extends App with LazyLogging {
  // values and variables
  @unused val aBoolean: Boolean = false
  
  // expressions
  @unused val anIfExpression = if (2 < 3) "bigger" else "smaller"
  
  // instructions vs expressions
  @unused val theUnit: Unit = println("Hello, Scala") // Unit = no meaningful value or void in other languages
  
  // functions
  @unused def myFunction(@unused x: Int) = 42
  
  // OOP
  class Animal
  @unused class Dog extends Animal
  
  trait Carnivore {
    def eat(animal: Animal): Unit
  }
  
  @unused class Crocodile extends Animal with Carnivore {
    override def eat(animal: Animal): Unit = println("Crunch!")
  }
  
  // Singleton Pattern
  @unused object MySingleton
  
  // Companions
  @unused object Carnivore
  
  // Generics
  @unused trait MyList[A]
  
  // Method Notation (infix, postfix, prefix)
  @unused val x = 1 + 2
  @unused val y = 1.+(2)
  
  // Functional Programming (working with functions, as with working with any other value)
  val incrementer: Int => Int = x => x + 1 // lambda
  val incremented = incrementer(42)
  logger.info(s"$incremented")
  
  // Map, flatMap, filter ( Higher-level functions )
  val processedList = List(1, 2, 3).map(incrementer)
  println(processedList)
  
  // Pattern Matching
  @nowarn("msg=Access can be private") val unknown: Any = 45
  @unused val ordinal = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }
  
  // Try / Catch
  val result = try {
    throw new NullPointerException
  } catch {
    case _: NullPointerException => "Some returned value"
    case _ => "Something else"
  }
  logger.info(result)
  
  // Futures
  import scala.concurrent.ExecutionContext.Implicits.global
  
  val aFuture = Future {
    // some expensive computation, run on another thread
    TimeUnit.SECONDS.sleep(5)
    42
  }
  
  aFuture.onComplete {
    case Success(meaningOfLife) => logger.info(s"I've found the meaning of life: $meaningOfLife")
    case Failure(exception) => logger.info(s"I've failed: $exception")
  }
  
  // Partial functions
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 43
    case 8 => 56
    case _ => 999
  }
  logger.info(s"${aPartialFunction(1)}")
  
  // Implicits
  //  * auto-injection by the compiler
  def methodWithImplicitArg(implicit x: Int) = x + 43
  implicit val implicitInt: Int = 67
  val implicitCall = methodWithImplicitArg
  
  logger.info(s"$implicitCall")
  
  //  * Implicit conversions - implicit def
  case class Person(name: String) {
    def greet(): Unit = logger.info(s"Hi, my name is $name")
  }
  
  implicit def fromStringtoPerson(name: String): Person = Person(name)
  "Bob".greet()
  "Isaac".greet()
  
  //  * Implicit conversion - implicit classes
  implicit class Cat(name: String) {
    def meow(): Unit = logger.info("Meow")
  }
  "Quechoni".meow()
  
  /*
    - Where does the compiler look for implicits?
      - Local scope
      - Imported scope
      - Companion objects of the types involved in the method call
   */
  @unused val sortedList = List(3, 2, 1).sorted
}
