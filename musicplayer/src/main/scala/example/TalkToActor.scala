package example

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.duration._

import example.Checker.{BlackUser, CheckUser, WhiteUser}
import example.Recorder.NewUser
import example.Storage.AddUser

case class User(username: String, email: String)

object Recorder {
  sealed trait RecorderMsg

  case class NewUser(user: User) extends RecorderMsg

  def props(checker: ActorRef, storage: ActorRef) =
    Props(new Recorder(checker, storage))
}

object Checker {
  sealed trait CheckerMsg
  case class CheckUser(user: User) extends CheckerMsg

  sealed trait CheckerResponse

  case class BlackUser(user: User) extends CheckerMsg
  case class WhiteUser(user: User) extends CheckerMsg
}

object Storage {
  sealed trait StorageMsg
  case class AddUser(user: User) extends StorageMsg
}

class Storage extends Actor {
  var users = List.empty[User]

  def receive: Receive = {
    case AddUser(user) => {
      println(s"Storage: $user added")
      users = user :: users
    }
  }
}

class Checker extends Actor {
  val blackList = List(
    User("Adam", "adam@email.com")
  )

  def receive = {
    case CheckUser(user) if blackList.contains(user) =>
      println(s"Checker: $user in the blacklist")
      sender() ! BlackUser(user)
    case CheckUser(user) =>
      println(s"Checker: $user NOT in blacklist")
      sender() ! WhiteUser(user)
  }
}

class Recorder(checker: ActorRef, storage: ActorRef) extends Actor {
  import Recorder._
  import scala.concurrent.ExecutionContext.Implicits.global

  implicit val timeout = Timeout(5 seconds)

  def receive = {
    case NewUser(user) =>
      checker ? CheckUser(user) map {
        case WhiteUser(user) =>
          storage ! AddUser(user)
        case BlackUser(user) =>
          println(s"Recorder: $user in the blacklist")
      }
  }
}

class TalkToActor extends App {
  val system = ActorSystem("talk-to-actor")

  val checker = system.actorOf(Props[Checker], "checker")
  val storage = system.actorOf(Props[Storage], "checker")

  val recorder = system.actorOf(Recorder.props(checker, storage), "recorder")

  recorder ! Recorder.NewUser(User("Jhon", "jon@email.com"))
  recorder ! Recorder.NewUser(User("Adam", "adam@email.com"))
  recorder ! Recorder.NewUser(User("Adam2", "adam2@email.com"))

  Thread.sleep(100)

//  system.terminate()
  system.shutdown()

}
