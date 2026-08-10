package Main

import ui.argumentParser.MyArgumentParser
import ui.controller.ConsoleController
import ui.view.ConsoleView

@main def main (args: String*): Unit = {

  val view = new ConsoleView()
  val argParser = new MyArgumentParser(args.toList)
  val controller = new ConsoleController(argParser, view)
  controller.run()
}

//run --image "src/main/scala/resources/pikachu.jpg" --rotate +90 --scale 0.25 --invert --output-console