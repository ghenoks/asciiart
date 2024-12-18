package Main

import ui.argumentParser.MyArgumentParser
import ui.controller.ConsoleController

@main def main (args: String*): Unit = {

  val argParser = MyArgumentParser(args.toList)
  val controller = ConsoleController(argParser)
  controller.run()

  // Modely -> Option[Model], companion object
  // Business error
  // zkontrolovat vsechny moduly pracuji spravne
  // komentare
  // testy
}
