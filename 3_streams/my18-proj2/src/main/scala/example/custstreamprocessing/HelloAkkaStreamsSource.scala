package example.custstreamprocessing

import akka.stream.stage._
import akka.stream.{Attributes, Outlet, SourceShape}

class HelloAkkaStreamsSource
  extends GraphStage[SourceShape[String]] {

    val out: Outlet[String] = Outlet("SystemInputSource")
    override val shape: SourceShape[String] = SourceShape(out)

  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = new GraphStageLogic(shape) {

    setHandler(out, new OutHandler {
      override def onPull(): Unit = {
        val line = "Hello World Akka Streams!"
        push(out, line)
      }
    })

  }
}
