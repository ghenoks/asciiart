package ui.controller

import ui.{ArgumentParser, ModuleGetter}
import ui.handler.*

class ConsoleController (args: List[String]) extends Controller {
  override def run(): Unit = {
    val argParser = ArgumentParser(args)

    val result = for {
      parsedArgsList <- argParser.parseArgs()
      moduleGetter = ModuleGetter(parsedArgsList)
      modules <- moduleGetter.getModules
    } yield modules

    result match {
      case Right(modules) =>
        val exportHandler = ExportHandler(modules.getExporters, EndHandler)
        val asciiHandler = ASCIIHandler(modules.getASCII, exportHandler)
        val filterHandler = FilterHandler(modules.getFilter, asciiHandler)
        val greyHandler = GreyScaleHandler(modules.getGrey, filterHandler)
        val adapterHandler = ImageToRGBHandler(greyHandler)
        val loadHandler = LoadHandler(modules.getLoader, adapterHandler)

        loadHandler.handle("Anything")

      case Left(errorMessage) =>
        println(s"Error: $errorMessage")
    }
  }
}
