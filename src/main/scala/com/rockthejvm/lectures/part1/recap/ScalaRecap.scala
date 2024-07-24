package com.rockthejvm.lectures.part1.recap

import com.typesafe.scalalogging.LazyLogging

class ScalaRecap extends LazyLogging {
  // values and variables
  val aBoolean: Boolean = false
  
  // expressions
  val anIfExpression = if (2 < 3) "bigger" else "smaller"
  
  // instructions vs expressions
  val theUnit = println("Hello, Scala") // Unit = no meaningful value or void in other languages
  
  // functions
  def myFunction(x: Int) = 42
  
  // OOP
  class Animal
  
  class Cat extends Animal
  
  trait Carnivore {
    def eat(animal: Animal): Unit
  }
  
  class Crocodile extends Animal with Carnivore {
    override def eat(animal: Animal): Unit = println("Crunch!")
  }
  
  // singleton pattern
  object MySingleton
  
  //companion
  object Carnivore
  
  // generics (type invariance, covariance, contravariance)
  trait MyList[A]
  
  // method notation (infix, postfix, prefix)
  val x = 1 + 2
  val y = 1.+(2)
  
  // FP (working with functions, as with working with any other value)
  val incrementer: Int => Int = x => x + 1 // lambda
  
  val processedList = List(1, 2, 3).map(incrementer)
  
  // Pattern Matching
  val unknown: Any = 45
  val ordinal = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }
  
  // try-catch
  try {
    throw new NullPointerException
  } catch {
    case e: NullPointerException => "some returned value"
    case _ => "something else"
  }
  
  // Futures
  
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future
  import scala.util.{Failure, Success}
  
  val aFuture = Future {
    // some expensive computation, runs on another thread
    42
  }
  
  aFuture.onComplete {
    case Success(meaningOfLife) => println(s"I've found $meaningOfLife")
    case Failure(exception) => println(s"I've failed: $exception")
  }
  
  // Partial functions
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 43
    case 8 => 56
    case _ => 999
  }
  
  // implicits
  // auto-injection by the compiler
  def methodWithImplicitArgument(implicit x: Int) = x + 43
  
  implicit val implicitInt = 67
  val implicitCall = methodWithImplicitArgument
  
  // implicit conversion - implicit defs
  case class Person(name: String) {
    def greet(): Unit = println(s"Hi, my name is $name")
  }
  
  implicit def fromStringToPerson(name: String) = Person(name)
  
  "Bob".greet
  
  // implicit conversion - implicit classes
  implicit class Dog(name: String) {
    def bark(): Unit = println("Bark!")
  }
  
  "Lassie".bark
  
  /*
    - Where does the compiler look for implicits?
      - Local scope
      - Imported scope
      - Companion objects of the types involved in the call
   */
  List(3, 2, 1).sorted
}
