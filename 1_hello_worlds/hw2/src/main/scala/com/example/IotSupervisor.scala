package com.example

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{ActorSystem, Behavior, PostStop, Signal}


object IotSupervisor {
  def apply(): Behavior[Nothing] =
    Behaviors.setup[Nothing](context =>
      new IotSupervisor(context))
}

class IotSupervisor(context: ActorContext[Nothing])
  extends AbstractBehavior[Nothing](context) {

  override def onMessage(msg: Nothing): Behavior[Nothing] =
    Behaviors.unhandled

  override def onSignal: PartialFunction[Signal, Behavior[Nothing]] = {
    case PostStop =>
      context.log.info("IoT Application stopped")
      this
  }
}

object IoTApp extends App {
  val actSystem: ActorSystem[Nothing] = ActorSystem[Nothing](IotSupervisor(), "iot-superviser")

  actSystem.terminate()
}