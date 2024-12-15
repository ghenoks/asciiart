package UI

import scala.collection.mutable.ListBuffer

class ArgumentParser (args: List[String]) {

  def parseArgs(): Either[String, List[(String, Option[String])]] = {
    var parameter = ""
    val arguments = new ListBuffer[(String, Option[String])]()

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
          return Left("Invalid input: unexpected value detected without command")
        }
      }
    })

    if (parameter.nonEmpty) {
      arguments += ((parameter, None))
    }
    Right(arguments.toList)
  }
}
