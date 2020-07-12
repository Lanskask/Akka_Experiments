package com.example

import akka.actor.typed.{ActorSystem, Behavior, Signal}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

import akka.actor.typed.ActorRef

object Device {
  sealed trait Command
  final case class ReadTemperature(replyTo: ActorRef[RespondTemperature]) extends Command
  final case class RespondTemperature(value: Option[Double])
}

