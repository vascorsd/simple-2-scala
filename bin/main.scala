import cats.effect._
import org.legogroup.woof.{*, given}


object App extends IOApp:
  
  def run(args: List[String]): IO[ExitCode] =
    val program = 
      for 
        logger <- configureLogger()
        given Logger[IO] = logger
        // some basic direct printlns
        _ <- IO.println(s"${Cross.stuff}") *>
               IO.println("heyy " + Util.version)

        _ <- IO.println(Cross.currentDirectory())

        // using logger from instance summoner
        _ <- Logger[IO].error("log error msg test")
        
        // using logger from value directly
        _ <- logger.info("this looks like more normal code")
      yield ()
    
    program.as(ExitCode.Success)

  def configureLogger(): IO[Logger[IO]] = 
    given Filter = Filter.everything
    given Printer = ColorPrinter()
  
    DefaultLogger.makeIo(Output.fromConsole)

  // def test(): IO[Unit] =
  //   import scala.scalanative.unsafe._

  //   type Vec = CStruct3[Double, Double, Double]
    
  //   val vec = stackalloc[Vec]()
  //   vec._1 = 10.0
  //   vec._2 = 20.0
  //   vec._3 = 30.0
    
  //   IO.println(vec._3)

