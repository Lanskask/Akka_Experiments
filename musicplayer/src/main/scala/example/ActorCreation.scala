package example

import akka.actor.{Actor, ActorSystem, Props}
import example.MusicController._
import example.MusicPlayer._

object MusicController {
  sealed trait ControllerMsg
  case object Play extends ControllerMsg
  case object Stop extends ControllerMsg

  def props = Props[MusicController]
}

class MusicController extends Actor {
 def receive = {
   case Play =>
     println("Music Started....")
   case Stop =>
     println("Music Stoped....")
   case _ =>
     println("Unknown Message..")
 }
}

object MusicPlayer {
  sealed trait PlayMsg
  case object StartMusic extends PlayMsg
  case object StopMusic extends PlayMsg
}

class MusicPlayer extends Actor {
  def receive = {
    case StopMusic =>
      println("I don't want to stop music")
    case StartMusic =>
      val controller = context.actorOf(MusicController.props, "music_controller_1")
      controller ! Play
  }
}

object Creation /*extends App */ {
  val system= ActorSystem("creation")

  val player = system.actorOf(Props[MusicPlayer], "player")

  player ! StartMusic
}