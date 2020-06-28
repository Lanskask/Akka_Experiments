package example

import org.scalatest.funsuite.AnyFunSuite

class SortLettersTest extends AnyFunSuite {
  // группы с одинаковым кол-вом символов могут быть в произвольном порядке,
  // например “qwe” или “eqw”
  val testData = Seq(
    ("a", "a"),
    ("", ""),
    ("asdasdaaaweqbbbbasdasd", Seq("aaaaaaassssddddbbbbwqe", "aaaaaaassssddddbbbbewq")),
  )


  test("Check Letters Sorting") {
    for {
      (input, answer) <- testData
    } yield {
      answer match {
        case answer: String => assert(SortLetters.sortLetters(input) == answer)
        case seq: Seq[_] => assert(seq.contains(SortLetters.sortLetters(input)))
      }

    }
  }


}
