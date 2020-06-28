package com.example

import akka.actor.typed.{ActorSystem, Behavior, PostStop, PreRestart, Signal, SupervisorStrategy}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

object SupervisingActor {
  def apply(): Behavior[String] =
    Behaviors.setup(context =>
      new SupervisingActor(context))
}

class SupervisingActor(context: ActorContext[String])
  extends AbstractBehavior[String](context) {

  private val child = context.spawn(
    Behaviors.supervise(SupervisedActor()).
      onFailure(SupervisorStrategy.restart)
    , name = "Supervised-Actor"
  )

  override def onMessage(msg: String): Behavior[String] =
    msg match {
      case "failChild" =>
        child ! "fail"
        this
    }
}

object SupervisedActor {
  def apply(): Behavior[String] =
    Behaviors.setup(context =>
      new SupervisedActor(context))
}

class SupervisedActor(context: ActorContext[String])
  extends AbstractBehavior[String](context) {

  override def onMessage(msg: String): Behavior[String] = msg match {
    case "fail" =>
      println("supervised actor fails now")
      throw new Exception("Just failed exeption")
  }

  override def onSignal: PartialFunction[Signal, Behavior[String]] = {
    case PreRestart =>
      println("supervised actor restarting")
      this
    case PostStop =>
      println("supervised actor stopped")
      this
  }
}



object SupervisingEx extends App {
  val firstActor = ActorSystem(SupervisingActor(), "firts-actor")
  firstActor ! "failChild"

  firstActor.terminate()
}