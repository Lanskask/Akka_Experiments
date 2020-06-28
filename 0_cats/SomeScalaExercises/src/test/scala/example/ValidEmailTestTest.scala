package example

import org.scalatest.funsuite.AnyFunSuite

class ValidEmailTest extends AnyFunSuite {
  val testData = Seq(
    ("test@gmail.com", true),
    ("", false),
    ("testgmail.com", false),
    ("test.gmail.com", false),
    ("t es t@gmailcom", false),
    ("b ob @tes tmai l.com", false),
    ("t es t@gmailcom", false),
    ("b ob @tes tmai l.com", false),
    ("test@gmailcom", false),
  )

  test("Test Email check") {
    for {
      (k, v) <- testData
    } {
      assert(ValidEmail.validEmail(k) == v)
    }
  }
}
