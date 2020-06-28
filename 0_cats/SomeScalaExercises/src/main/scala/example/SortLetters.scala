package example

object SortLetters extends App {
  def sortLetters(str: String) = str match {
    case "" => ""
    case str if str.length == 1 => str
    case _ => str.toSeq.
      groupBy(identity).toSeq.
      sortBy{case (k,v) => -v.size}.
      map{ case (k,v) => v.toString}.
      mkString
  }
}
