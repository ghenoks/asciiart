package ui.argumentParser

import models.Argument
import ui.argumentParser.ArgumentParser

import scala.collection.mutable.ListBuffer

class MyArgumentParser(args: List[String]) extends ArgumentParser {

  override def parseArgs(): Either[String, List[Argument]] = {
    var parameter = ""
    val arguments = new ListBuffer[Argument]()

    var error: Option[String] = None

    args.foreach (command => {
      if (command.startsWith("--")) {
        if (parameter.nonEmpty) {
          arguments += Argument(parameter, None)
          parameter = ""
        }
        parameter = command.substring(2)
      }

      else {
        if (parameter.nonEmpty) {
          arguments += Argument(parameter, Some(command))
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
      case Some(errMsg) => Left(errMsg)
      case None => Right(arguments.toList)
    }
  }
}
