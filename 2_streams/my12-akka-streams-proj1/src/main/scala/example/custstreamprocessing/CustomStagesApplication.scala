package example.custstreamprocessing
//package example.custstreamprocessing.CustomStagesApplication

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

import scala.io.StdIn


class CustomStagesApplication extends App {
  implicit  val actorSystem = ActorSystem("CustomStages")
  implicit  val actorMaterializer = ActorMaterializer()

  val source = Source.fromGraph(new HelloAkkaStreamsSource)
  val upperCaseMapper = Flow[String].map(_.toUpperCase)
  val splitter = Flow[String].mapConcat(_.split(" ").toList)
  val punctuationMapper = Flow[String].map(_.replaceAll(
    """[p{Punct}&&[^.]]""", ""
    ).replaceAll(System.lineSeparator(), ""))
  val filterEmptyEllements = Flow[String].filter(_.nonEmpty)
  val wordCounterSink = Sink.fromGraph(new WordCounterSink)


  val stream = source
    .via(upperCaseMapper)
    .via(splitter)
    .via(punctuationMapper)
    .via(filterEmptyEllements)
    .to(wordCounterSink)

  stream.run()

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine()
  actorSystem.terminate()

}
