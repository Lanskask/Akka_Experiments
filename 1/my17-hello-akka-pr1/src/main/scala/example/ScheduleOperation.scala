package example

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.duration._

object ScheduleOperation extends App {
	val actSystem = ActorSystem("ScheduleOperation")

	import actSystem.dispatcher

//  actSystem.scheduler.scheduleOnce(10 seconds) {
//    println(s"Sum of (1 + 2) is ${1 + 2}")
//  }
//
//  actSystem.scheduler.schedule(11 seconds, 2 seconds) {
//    println(s"Hello, Sorry for disturbing you every 2 seconds")
//  }

  val actor = actSystem.actorOf(Props[RandomIntAdder], "RandomIntAdder")

  actSystem.scheduler.scheduleOnce(10 seconds, actor, "tick")

  actSystem.scheduler.scheduleOnce(1 minute) {
    actSystem.terminate
  }
  actSystem.scheduler.schedule(11 seconds, 2 seconds, actor, "tick")

}

class RandomIntAdder extends Actor {
  val r = scala.util.Random

  override def receive: Receive = {
    case "tick" =>
      val randomIntA = r.nextInt(1000)
      val randomIntB = r.nextInt(1000)
      println(s"$randomIntA + $randomIntB = ${Math.pow(randomIntA, randomIntB)}")
  }
}