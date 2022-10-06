import cats.effect._

object App extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
  //  test() *>
    IO.println(s"${Cross.stuff}") *>
      IO.println("heyy " + Util.version).as(ExitCode.Success)

/*  def test(): IO[Unit] = {
    type Vec = CStruct3[Double, Double, Double]
    
    val vec = stackallo[Vec]()
    vec._1 = 10.0
    vec._2 = 20.0
    vec._3 = 30.0
    length(vec)
    
    
  }
*/
}


