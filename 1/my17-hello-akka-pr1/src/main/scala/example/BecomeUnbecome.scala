package example

import akka.actor.{Props, Actor, ActorSystem}

object BecomeUnbecome extends App {
	val actorSystem = ActorSystem("BecomeUnbecomeSystem")

	val becUnbecAct = actorSystem.actorOf(Props[BecomeUnBecomeActor], "BecomeUnBecomeActor")

	becUnbecAct ! true
	becUnbecAct ! "hello how are you?"
	becUnbecAct ! false
	becUnbecAct ! 1100
	becUnbecAct ! "2 hello how are you?"
	becUnbecAct ! true
	becUnbecAct ! "What are you"

	actorSystem.terminate
}

class BecomeUnBecomeActor extends Actor {
	def receive: Receive = {
		case true => context.become(isStateTrue)
		case false => context.become(isStateFalse)
		case _ => 
			println(s"${self.path} receive: don't want what to say")
	}
	def isStateTrue: Receive = {
		case msg: String => println(s"$msg")
		case false => 
			println(s"${self.path} isStateTrue become(isStateFalse)")
			context.become(isStateFalse)
		case _ => println("isStateTrue: don't want what to say")
	}
	def isStateFalse: Receive = {
		case msg => println(s"$msg")
		case true => 
			println(s"${self.path} isStateFalse become(isStateTrue)")
			context.become(isStateTrue)
		case _ => println("isStateFalse: don't want what to say")
	}
}