//> using target.platform "jvm"

import scala.util.*


object Cross:
  def stuff: String =
    "jvm cross compile"
  
  def currentDirectory(): String =
    Properties.userDir
