package part1recap

import java.util.concurrent.TimeUnit
import scala.concurrent.Future
import scala.util.{Failure, Success}

object ScalaRecap extends App {
  // values and variables
  val aBoolean: Boolean = false
  
  // expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"
  
  // instructions vs expressions
  val theUnit = println("Hello, Scala!") // Unit => No meaningful value ( void in other languages)
  
  // functions
  def myFunctions(x: Int) = 42
  
  // OOP
  class Animal
  class Dog extends Animal
  trait Carnivore {
    def eat(animal: Animal)
  }
  
  class Crocodile extends Animal with Carnivore {
    override def eat(animal: Animal): Unit = println("Crunch")
  }
  
  // Singleton pattern
  object MySingleton
  
  // Companions
  object Carnivore
  
  // Generics
  trait MyList[A]
  
  // Method notation
  val x = 1 + 2
  val y = 1.+(2)
  
  // Functional Programming
  val incrementer: Int => Int = x => x + 1
  val incremented = incrementer(42)
  println(incremented)
  
  // Map, flatMap, filter ( Higher-level functions )
  val processedList = List(1, 2, 3).map(incrementer)
  println(processedList)
  
  // Pattern Matching
  val unknown: Any = 45
  val ordinal = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }
  
  // Try-catch
  val result = try {
    throw new NullPointerException
  } catch {
    case _: NullPointerException => "Some returned value"
    case _ => "Something else"
  }
  println(result)
  
  // Future
  
  import scala.concurrent.ExecutionContext.Implicits.global
  
  val aFuture = Future {
    // some expensive computation, run on another thread
    TimeUnit.SECONDS.sleep(5)
    42
  }
  
  aFuture.onComplete {
    case Success(meaningOfLife) => println(s"I've found the meaning of life: $meaningOfLife")
    case Failure(exception) => println(s"I've failed: $exception")
  }
  
  // Partial functions
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 43
    case 8 => 56
    case _ => 999
  }
  println(aPartialFunction(1))
  
  // Implicits
  //  * auto-injection by the compiler
  def methodWithImplicitArg(implicit x: Int) = x + 43
  implicit val implicitInt = 67
  val implicitCall = methodWithImplicitArg
  
  println(implicitCall)
  
  //  * Implicit conversions - implicit def
  case class Person(name: String) {
    def greet: Unit = println(s"Hi, my name is $name")
  }
  
  implicit def fromStringtoPerson(name: String) = Person(name)
  
  "Bob".greet
  "Isaac".greet
  
  //  * Implicit conversion - implicit classes
  implicit class Cat(name: String) {
    def meow: Unit = println("Meow")
  }
  
  "Quechoni".meow
  
  //  * Where does the compiler look for implicits?
  //    - local scope
  //    - imported scope
  //    - companion objects of the types involved in the method call
}
