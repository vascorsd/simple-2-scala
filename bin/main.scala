import cats.effect._

object App extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    IO.println("heyy").as(ExitCode.Success)
}
