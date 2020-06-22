import akka.actor.{Actor, ActorRef, ActorSystem, Inbox, Props}
import scala.concurrent.duration._
import akka.actor._
import akka.pattern.after

//import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future, Promise}

import scala.concurrent.{ExecutionContext, Future}


object RetryBackoff extends App {
  val as = ActorSystem("timer")

  val scheduler: Scheduler = as.scheduler
  val ec: ExecutionContext = as.dispatcher

  def retryBackoff[T](op: => T, backOffs: Seq[FiniteDuration])
                     (implicit s: Scheduler, ec: ExecutionContext) : Future[T] = {
    Future(op)(ec) recoverWith {
      case _ if backOffs.nonEmpty =>
        after(backOffs.head, scheduler)(retryBackoff(op, backOffs.tail))
    }
  }

  def calc(): Int = {
    if(time > 3) 10
    else {
      time += 1
      println("Not yet!")
      throw new IllegalArgumentException("not yet")
    }
  }


  var time = 0

  val f5 = retryBackoff(calc(), Seq(500.millis, 500.millis, 1.second, 2.seconds))(scheduler, ec)
  println(Await.ready(f5, 10.seconds))
  println(Await.result(f5, 10.seconds))

  as.terminate()
}
