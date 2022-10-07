//> using target.platform "scala-native"

import scala.scalanative.unsafe._
import scala.scalanative.libc._

object Cross:
  def stuff: String =
    "native cross compile"

  def currentDirectory(): String =
    NativeHelpers.moveIntoScalaMemory {
      unistd.get_current_dir_name()
    }


@extern
object unistd:
  // allocates array big enough to hold the absolute pathname of the current directory.
  // if env variable PWD is set, and the value is correct, then that value is returned.
  // the caller should free the returned buffer.
  def get_current_dir_name(): CString = extern

object NativeHelpers:
  // Convert an heap allocated CString (ptr and terminated by NUL) from the C-Side 
  // into a regular String in the Scala-Side. After the conversoin deallocates the
  // now unneeded CString by free the memore where it was at.
  def moveIntoScalaMemory(cs: CString): String =
    val converted = fromCString(cs)
    stdlib.free(cs)
  
    converted