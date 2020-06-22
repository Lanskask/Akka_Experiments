package example

import akka.actor.{Props, Actor, ActorSystem, ActorRef}
// import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._

import scala.util.Random._

object RandResiver extends App {
	import Messages._ 

	val actorSystem = ActorSystem("RandResiverActSystem")

	val randNumGenerator = actorSystem.actorOf(Props[RandomNuberGeneratorActor], "RandomNuberGeneratorActor")

	val queryActor = actorSystem.actorOf(Props[QueryActor], "QueryActor")

	queryActor ! Start(randNumGenerator)

	actorSystem.terminate
}

class QueryActor extends Actor {
	import Messages._

	override def receive: Receive = {
		case Start(actorRef) => println(s"send me the next rand number")
			actorRef ! GiveMeRandomNumber
		case Done(randomNumber) => 
			println(s"resieve a rand num $randomNumber")
	}
}

class RandomNuberGeneratorActor extends Actor {
	import Messages._

	override def receive: Receive = {
		case GiveMeRandomNumber => 
			println("recieved a message to generate a random integer")
			val randomNumer = nextInt
			sender ! Done(randomNumer)
		case _ => 
			println("Some wrong is sended")
	}
}

object Messages {
	case class Done(randomNumbere: Int)
	case object GiveMeRandomNumber
	case class Start(actorRef: ActorRef)
}