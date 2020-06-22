package example

import akka.actor.{PoisonPill, Props, Actor, ActorSystem}

object StopActorApp extends App {
	val actorSystem = ActorSystem("StopActorApp")

	val shutDownActor = actorSystem.actorOf(Props[ShutDownActor], "ShutDownActor")

	shutDownActor ! "hello"
	shutDownActor ! PoisonPill
	shutDownActor ! "hello2"
	
	
	val shutDownActor2 = actorSystem.actorOf(Props[ShutDownActor], "ShutDownActor2")

	shutDownActor2 ! "hello"
	shutDownActor2 ! Stop
	shutDownActor2 ! "hello"

	actorSystem.terminate
}

class ShutDownActor extends Actor {
	override def receive: Receive = {
		case msg: String => println(s"$msg")
		case Stop => context.stop(self)
	}
}

case object Stop