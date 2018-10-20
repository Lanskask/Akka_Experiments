package example

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
//import akka.stream.javadsl.JavaFlowSupport.Source
import akka.stream.scaladsl._



object FilesStream extends App {
  implicit val actorSystem = ActorSystem("FilesStream")
  implicit val actorMaterializer = ActorMaterializer

  println("Helo")

//  val fileList = Source(file)
}
