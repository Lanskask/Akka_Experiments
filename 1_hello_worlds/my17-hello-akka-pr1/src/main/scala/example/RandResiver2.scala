package example

import akka.actor.{Props, Actor, ActorSystem, ActorRef}
// import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._

import scala.util.Random._

object RandResiver2 extends App {
	import Messages2._ 

	val actorSystem = ActorSystem("RandResiver2ActSyst")

	val QueryActor2 = actorSystem.actorOf(Props[QueryActor2], "QueryActor2")
	val ranNumGenActor = actorSystem.actorOf(Props[RandomNumGeneratorActor], "randomNumGeneratorActor")

	QueryActor2 ! Start2(ranNumGenActor)

	actorSystem.terminate 
}

class QueryActor2 extends Actor {
	import Messages2._ 
	
	override def receive: Receive = {
		case Done2(randNum) =>
			println(s"RandNumber: $randNum")
		case Start2(actorRef) =>
			println("In the QueryActor2 - case Start2")
			actorRef ! GiveMeRandomNumber2
	}
}

class RandomNumGeneratorActor extends Actor {
	import Messages2._ 
	
	override def receive: Receive = {
		case GiveMeRandomNumber2 =>
			println("Making Ran number")
			val randNum = nextInt
			sender ! Done2(randNum)
		case _ => println(s"Resieved someth wrong")
	}
}


object Messages2 {
	case class Done2(randNum: Int)
	case object GiveMeRandomNumber2
	case class Start2(actorRef: ActorRef)
}