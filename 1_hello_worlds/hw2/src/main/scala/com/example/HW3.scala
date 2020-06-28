package com.example

import akka.actor.typed.{ActorSystem, Behavior, Signal}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

object PrintMyActorRefActor {
  def apply(): Behavior[String] =
    Behaviors.setup(context => new PrintMyActorRefActor(context))
}

class PrintMyActorRefActor(context: ActorContext[String]) extends
  AbstractBehavior[String](context) {

  override def onMessage(msg: String): Behavior[String] =
    msg match {
      case "printit" =>
        val secondRef = context.spawn(Behaviors.empty[String], "seocnd-actor")
        println(s"Second $secondRef")
        this
    }
}

object Main {
  def apply(): Behavior[String] =
    Behaviors.setup(context => new Main(context))
}

class Main(context: ActorContext[String]) extends AbstractBehavior[String](context) {

  override def onMessage(msg: String): Behavior[String] =
    msg match {
      case "start" =>
        val firstRef = context.spawn(PrintMyActorRefActor(), "first-actor")
        println(s"First $firstRef")
        firstRef ! "printit"
        this
      case _ =>
        println(s"Some other message came to the main actor")
        this
    }
}
object ActorHierarchyExampleExperiments extends App {
  val testSystem = ActorSystem(Main(), "testSystem")
  testSystem ! "start"

  testSystem.terminate()
}