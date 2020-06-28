//package example.fromvideolecture

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Keep, Sink, Source}
import akka.stream.{ActorMaterializer, ThrottleMode}

import scala.concurrent.Await
import scala.concurrent.duration._


object Simple1 extends App {

  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()
//  implicit val executor = ExecutionContext()
  import system.dispatcher

  val str = "Learn Akka Streams!"

  val done =
  Source
    .repeat(str)
    .zip(Source.fromIterator(() => Iterator.from(0)))
    .take(7)
    .mapConcat { case (s, n) =>
      val i = " " * n
      f"$i$s%n"
    }
    .throttle(str.length, 500.millis, 1, ThrottleMode.Shaping)
//    .to(Sink.foreach(print))
    .toMat(Sink.foreach(print))(Keep.right)
    .run()

  done.onComplete(_ => system.terminate)

  Await.ready(system.whenTerminated, Duration.Inf)
}