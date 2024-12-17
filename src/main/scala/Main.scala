package Main

import ui.controller.ConsoleController

@main def main (args: String*): Unit = {
  val controller = ConsoleController(args.toList)
  controller.run()
}
