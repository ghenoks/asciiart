package ui.controller

import ui.argumentParser.{ArgumentParser, MyArgumentParser}
import ui.handler.*
import ui.moduleGetter.MyModuleGetter
import ui.view.View

class ConsoleController (argParser: ArgumentParser, view: View) extends Controller {
  override def run(): Unit = {

    val result = for {
      parsedArgsList <- argParser.parseArgs()
      moduleGetter = MyModuleGetter(parsedArgsList)
      modules <- moduleGetter.getModules
    } yield modules

    result match {
      case Right(modules) =>
        val exportHandler = ExportHandler(modules.getExporter, EndHandler)
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
