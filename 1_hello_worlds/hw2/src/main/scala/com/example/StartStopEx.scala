package com.example

import akka.actor.typed.{ActorSystem, Behavior, PostStop, Signal}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

object StartStopActor1 {
  def apply(): Behavior[String] =
    Behaviors.setup(context => new StartStopActor1(context))
}

class StartStopActor1(context: ActorContext[String]) extends
  AbstractBehavior[String](context) {

  println("first start-stop-actor started")
  context.spawn(StartStopActor2(), "second")
  context.spawn(StartStopActor3(), "third")

  override def onMessage(msg: String): Behavior[String] =
    msg match {
      case "stop" => Behaviors.stopped
    }

  override def onSignal: PartialFunction[Signal, Behavior[String]] = {
    case PostStop =>
      println("first stopped")
      this
  }
}

object StartStopActor2 {
  def apply(): Behavior[String] =
    Behaviors.setup(new StartStopActor2(_))
}

class StartStopActor2(context: ActorContext[String]) extends
  AbstractBehavior[String](context) {

  println("second start-stop-actor started")

  override def onMessage(msg: String): Behavior[String] =
    Behaviors.unhandled

  override def onSignal: PartialFunction[Signal, Behavior[String]] = {
    case PostStop =>
      println("second stopped")
      this
  }
}

object StartStopActor3 {
  def apply(): Behavior[String] =
    Behaviors.setup(new StartStopActor3(_))
}

class StartStopActor3(context: ActorContext[String]) extends
  AbstractBehavior[String](context) {

  println("the third start-stop-actor started")

  override def onMessage(msg: String): Behavior[String] =
    Behaviors.unhandled

  override def onSignal: PartialFunction[Signal, Behavior[String]] = {
    case PostStop =>
      println("the third stopped")
      this
  }
}

object StartStopExperiments extends App {
  val firstStartStopActor = ActorSystem(StartStopActor1(), "firts-actor")
  firstStartStopActor ! "stop"

  firstStartStopActor.terminate()
}