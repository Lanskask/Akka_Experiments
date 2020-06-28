//#full-example
package com.example


import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object AkkaQuickstart extends App {
  implicit val system = ActorSystem("AkkaStreamsRecap")
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  // 1 // source, 
  val source = Source(1 to 1000)
  val flow = Flow[Int].map(x => x + 1)
  val sink = Sink.foreach[Int]( x => print(s"$x, "))

  val runnableGraph = source.via(flow).to(sink)
//  val simpleMaterializedGraph = runnableGraph.run()

  val sumSink = Sink.fold[Int, Int](0)( (res, x) => res + x)
  /*val sumFuture = source.runWith(sumSink)

  sumFuture.onComplete {
    case Success(sum) => println(s"It's a sum: $sum")
    case Failure(ex) => println(s"It's a FAILURE: $ex")
  }*/

//  val sumRes = Await.result(sumFuture, 1.second)
//  println(s"sumRes: $sumRes")

/*
  val anotherMaterializedValues =
    source.viaMat(flow)(Keep.right). // take materialized results from flow
      toMat(sink)(Keep.left). // take materialized value of `source.viaMat(flow)` -> mat val of Flow
      run()
*/


  /*
  * Backpressure action variants
  * - buffer elements
  * apply a strategy in case the buffer overflows
  * fail the entire stream
  * */
  val bufferedFlow = Flow[Int].buffer(10, OverflowStrategy.dropHead)

  source.async
      .via(bufferedFlow).async
      .runForeach{ x =>
        Thread.sleep(10)
        print(s"$x, ")
      }



  system.terminate()
}
