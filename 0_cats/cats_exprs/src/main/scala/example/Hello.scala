package example

import cats._
import cats.Semigroup
import cats.syntax.foldable._
import cats.Eval.FlatMap
import cats.implicits._
import cats.syntax.semigroupal._
import cats.instances.string._ // for Monoid
import scala.util.Either
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global // required by Monad[Future]
import cats.instances.future._ // Monad for `Future`
import cats.syntax.apply._ // `tupled` and `mapN`
import cats.data.EitherT

object Hello extends App {


}

class Hello {
  def run1(): Unit = {
    println(Semigroup[Int].combine(1, 2))
    println(Semigroup[List[Int]].combine(List(1, 2, 3), List(4, 5, 6)))
    println(Semigroup[Option[Int]].combine(Option(1), Option(2)))
    println(Semigroup[Option[Int]].combine(Option(1), None))

  }

  def run2(): Unit = {
    println(foldMap(Vector(1, 2, 3))(_.toString + "! "))
  }

}
