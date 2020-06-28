package com.example

import akka.actor.{Actor, ActorSystem, Props}

case class WhoToGreet(who: String)

class GreeterActor extends Actor {
  def receive = {
    case WhoToGreet(who) => println(s"Hello $who")
  }
}

object HW2 extends App {
//  val greeterMain: ActorSystem[GreeterMain.SayHello] = ActorSystem(GreeterMain(), "AkkaQuickStart")
  val system = ActorSystem("Hello-Akka")
  val greeterActor = system.actorOf(Props[GreeterActor], "greeter")
  greeterActor ! WhoToGreet("Pavel")

  system.terminate()
}
