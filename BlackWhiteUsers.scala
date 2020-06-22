
trait Printable[A] {
  def printThis[A](): Unit = println(this.toString())
}

case class User(username: String, email: String) extends Printable[User]

object Recorder extends App {
  val users = List(User("Pavel", "email1"), User("Pavel2", "email12"))

  users map (_.printThis)
}