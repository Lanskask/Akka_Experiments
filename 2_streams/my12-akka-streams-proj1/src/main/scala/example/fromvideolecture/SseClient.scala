package example.fromvideolecture

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.unmarshalling.sse.EventStreamUnmarshalling
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
//import akka.stream.{ActorMaterializer, Materializer}

import akka.NotUsed
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.model.MediaTypes.`text/event-stream`
import akka.http.scaladsl.model.headers.{Accept, `Last-Event-ID`}
import akka.http.scaladsl.model.sse.ServerSentEvent
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer
import akka.stream.scaladsl.{Flow, Source}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object SseClient {
  /*def apply[A](
      uri: Uri,
      handler: Sink[ServerSentEvent, A],
      send: HttpRequest => Future[HttpResponse],
      lastEventId: Option[String] = None
  )(
              implicit ec: ExecutionContext, mat: Materializer
  ): Source[A, NotUsed] = {
    import EventStreamUnmarshalling._
    import mat.executionContext

    // Flow[Option[String], (Future[Option[ServerSentEvent]], A), NotUsed]
    def getAndHandleEvents = {
      def get(lastEventId: Option[String]) = {

        val request = {
          val r = Get(uri).addHeader(Accept(`text/event-stream`))
          lastEventId.foldLeft(r)((r, i) => r.addHeader(`Last-Event-ID`(i))) //headers.Accept
        }
          //Get(uri).addHeader(Accept(`text/event-stream`))
        send(request).flatMap(Unmarshal(_).to[Source[ServerSentEvent, Any]])
//        send(request).flatMap(Unmarshal(_).to[Source[ServerSentEvent, Any]]) //.fallbackTo(Future.successful(noEvents))

      }
      def handle(events: Source[ServerSentEvent, Any])= {
        events.runWith(handler)
      }
      Flow[Option[String]].mapAsync(1)(get).map(handle)
    }

    // Flow[Future[Option[ServerSentEvent]], Option[String], NotUsed]
    def currentLastEventId = {
      ???
    }

//    val continuousEvents = {
//      def getEventSource(lastEventId: Option[String]) = {
//        val request = {
//          val r = Get(uri).addHeader(Accept(`text/event-stream`))
//          lastEventId.foldLeft(r)((r, i) => r.addHeader(`Last-Event-ID`(i)))
//        }
//        send(request).flatMap(Unmarshal(_).to[EventSource]).fallbackTo(Future.successful(noEvents))
//      }
//      def recover(eventSource: EventSource) = eventSource.recoverWithRetries(1, { case _ => noEvents })
//      def delimit(eventSource: EventSource) = eventSource.concat(singleDelimiter)
//      Flow[Option[String]]
//        .mapAsync(1)(getEventSource)
//        .flatMapConcat((recover _).andThen(delimit))
//    }

    // Grahp with source shape
    Source.single(lastEventId).via(getAndHandleEvents)

  }

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val mat = ActorMaterializer()

    val config = system.settings.config
    val address = config.getString("lyas.sse-server.address")
    val port = config.getInt("lyas.sse-server.port")

    val client = SseClient (
      Uri(s"http://$address:$port"),
      Sink.foreach(println),
      Http().singleRequest(_),
      Some("10")
    )
    client.runWith(Sink.ignore)

    Await.ready(system.whenTerminated, Duration.Inf)


  }*/
}
