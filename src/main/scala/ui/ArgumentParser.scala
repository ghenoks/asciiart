package ui

import scala.collection.mutable.ListBuffer

class ArgumentParser (args: List[String]) {

  def parseArgs(): Either[String, List[(String, Option[String])]] = {
    var parameter = ""
    val arguments = new ListBuffer[(String, Option[String])]()

    var error: Option[String] = None

    args.foreach (command => {
      if (command.startsWith("--")) {
        if (parameter.nonEmpty) {
          arguments += ((parameter, None))
          parameter = ""
        }
        parameter = command.substring(2)
      }

      else {
        if (parameter.nonEmpty) {
          arguments += ((parameter, Some(command)))
          parameter = ""
        }
        else {
          error = Some("Invalid input: unexpected value detected without command")
        }
      }
    })

    if (parameter.nonEmpty) {
      arguments += ((parameter, None))
    }

    error match {
      case Some(errMsg) => Left(errMsg)
      case None => Right(arguments.toList)
    }
  }
}
