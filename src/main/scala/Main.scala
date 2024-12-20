package Main

import ui.argumentParser.MyArgumentParser
import ui.controller.ConsoleController
import ui.view.ConsoleView

@main def main (args: String*): Unit = {

  val view = new ConsoleView()
  val argParser = new MyArgumentParser(args.toList)
  val controller = new ConsoleController(argParser, view)
  controller.run()

  // komentare
  // testy done - models, exporters, filters, converters, visitor, view
  // testy to do - loaders, handlers, argumentParser, ModuleGetter, controller, helpers/traits?
}
