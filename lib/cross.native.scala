//> using target.platform "scala-native"

import scala.scalanative.unsafe._


object Cross:
  def stuff: String =
    "native cross compile"

  def currentDirectory(): String =
    fromCString(unistd.get_current_dir_name())


@extern
object unistd:
  // allocates array big enough to hold the absolute pathname of the current directory.
  // if env variable PWD is set, and the value is correct, then that value is returned.
  // the caller should free the returned buffer.
  def get_current_dir_name(): CString = extern