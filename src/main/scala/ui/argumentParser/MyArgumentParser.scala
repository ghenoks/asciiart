package ui.argumentParser

import models.{Argument, BusinessError}

import scala.collection.mutable.ListBuffer

/*
 * Parses arguments in the form of List[String] into List[Argument]
 * Argument is made up of name and value
 * Argument parser evaluates each argument and decides if its command (starts with "--") or value (doesn't have "--")
 * value always has to come after argument or returns BusinessError
 * Command doesn't have to have value after
 * ArgumentParser iterates through args and combines command with the value after it into one Argument
 */
class MyArgumentParser(args: List[String]) extends ArgumentParser {

  override def parseArgs(): Either[BusinessError, List[Argument]] = {
    var parameter = ""
    val arguments = new ListBuffer[Argument]()

    var error: Option[String] = None

    args.foreach (arg => {
      if (arg.startsWith("--")) {
        if (parameter.nonEmpty) {
          arguments += Argument(parameter, None)
          parameter = ""
        }
        parameter = arg.substring(2)
      }

      else {
        if (parameter.nonEmpty) {
          arguments += Argument(parameter, Some(arg))
          parameter = ""
        }
        else {
          error = Some("Invalid input: unexpected value detected without command")
        }
      }
    })

    if (parameter.nonEmpty) {
      arguments += Argument(parameter, None)
    }

    error match {
      case Some(errMsg) => Left(BusinessError(errMsg))
      case None => Right(arguments.toList)
    }
  }
}
