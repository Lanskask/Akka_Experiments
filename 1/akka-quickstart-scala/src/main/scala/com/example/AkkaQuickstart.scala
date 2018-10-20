package com.example

// com.example.AkkaQuickstart

import akka.actor.{ Actor, ActorSystem, Props }

case class Greeting(msg: String, name: String)

case class SayGoodbye(msg: String)

class HelloActor extends Actor {
  def receive = {
    case c: Greeting => println(s"hello ${c.name}; mess: ${c.msg}")
    case c: SayGoodbye => println(s"Bye You Too; mess: ${c.msg}")
  }
}

object AkkaQuickstart extends App {
  val system = ActorSystem("HelloSystem")

  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor1")
  helloActor ! Greeting("Welcome1", "Wally")
  helloActor ! SayGoodbye("GoodB")
}
