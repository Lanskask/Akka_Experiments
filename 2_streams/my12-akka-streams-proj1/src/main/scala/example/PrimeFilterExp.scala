package example

object PrimeFilterExp extends App {

  def nNumbs(n: Int): Unit = {
//    for {
//      i <- 1 to n
//      if isPrime(i)
//      if
//    } yield

  }

  def isPrime(n: Int): Boolean = {
    if (n <= 1) false
    else if (n == 2) true
    else !(2 until n).exists(x => n % x == 0)
  }

  (1 to 1000).filter(isPrime).filter(x => x.toString.reverse(0) == '3').sum

//  sc.parallelize((1 to 1000)).filter(isPrime).filter(x => x.toString.reverse(0) == '3').toDF.sum
//  // 18146
//
//  sc.parallelize((1 to 1000)).toDF.filter(x => isPrime(x.getInt)).filter(x => x.toString.reverse(0) == '3').sum
//
//  sc.parallelize((1 to 100)).toDF.map(x => x.getInt).map(print)
}
