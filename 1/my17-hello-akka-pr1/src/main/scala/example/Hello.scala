package example

import akka.actor.{Props, Actor, ActorSystem}
// import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._


object Hello extends App {
	val actorSystem = ActorSystem("HelloAkka1")
	// --- part 1
	// val myActor = actorSystem.actorOf(Props[MyActor])
	// println(myActor.path)

	// myActor ! "test"
	// myActor ! "test2"
	// --- end of part 1

	// --- part 2
	val fibActor = actorSystem.actorOf(Props[FibonacciActor])
	implicit val timeout = Timeout(10 seconds)
	val fibFuture = (fibActor ? 11).mapTo[Int]
	val fibonacciNumber = Await.result(fibFuture, 10 seconds)
	println(fibonacciNumber)
	// --- end of part 2
	
	actorSystem.terminate()
}

class FibonacciActor extends Actor {
	override def receive: Receive = {
		case num: Int => 
			val fibonacciNumber = fib(num)
			sender ! fibonacciNumber
	}

	def fib(n: Int): Int = n match {
		case 0 | 1 => n
		case _ => fib(n - 1) + fib(n - 2)
	}
}

// --- from P 1
class MyActor extends Actor {
  // val log = Logging(context.system, this)

  def receive = {
    case "test" => println("received test")
    case _      => println("received unknown message")
  }
}

class Hello3 extends Any {
	
	def sum2(ints: List[Int]): Int = {
		@annotation.tailrec
		def sumAcomulatored(ints: List[Int], acom: Int) = {
			ints match {
				case Nil => acom
				case x :: tail => sumAcomulatored(tail, acum + x)
			}
		}
		sumAcomulatored(ints, 0)
	}

}