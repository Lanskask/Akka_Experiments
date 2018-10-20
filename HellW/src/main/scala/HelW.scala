import akka.actor.{Actor, ActorSystem, Props}

case class WhoToGreet(who: String)

class Greeter extends Actor {
  def receive = {
    case WhoToGreet(who) => println(s"Hello $who")
    case _ => println("Something other!")
  }
}

object HelW extends App {
  val system = ActorSystem("Hello-Akka")

  val greeter = system.actorOf(Props[Greeter], "greeter_1")
  greeter ! WhoToGreet("AkkaAkka111")
  greeter ! 1.3
}