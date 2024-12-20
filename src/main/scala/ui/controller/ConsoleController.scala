package ui.controller

import ui.argumentParser.ArgumentParser
import ui.handler.*
import ui.moduleGetter.MyModuleGetter
import ui.view.View

/*
 * Controls the business operation
 * Has dependency on argumentParser and View
 */
class ConsoleController (argParser: ArgumentParser, view: View) extends Controller {
  override def run(): Unit = {
    
    /*
     * Argument parser parses the arguments it has
     * ModuleGetter gets the needed Modules according to the List[Argument] from argument parser
     */
    val result = for {
      parsedArgsList <- argParser.parseArgs()
      moduleGetter = MyModuleGetter(parsedArgsList)
      modules <- moduleGetter.getModules
    } yield modules
    
    /*
     * if all modules are valid give them to handlers
     */
    result match {
      case Right(modules) =>
        val exportHandler = ExportHandler(modules.getExporter, EndHandler)
        val asciiHandler = ASCIIHandler(modules.getASCII, exportHandler)
        val filterHandler = FilterHandler(modules.getFilter, asciiHandler)
        val greyHandler = GreyScaleHandler(modules.getGrey, filterHandler)
        val adapterHandler = ImageToRGBHandler(greyHandler)
        val loadHandler = LoadHandler(modules.getLoader, adapterHandler)
        
        /*
         * Run loading handler that will call its next handler etc. if nothing goes wrong
         * View shows successMessage if everything is good or errorMessage if something went wrong
         */
        loadHandler.handle("Anything") match {
          case Right(()) =>
            view.showSuccessMessage()
          case Left(error) =>
            view.showErrorMessage(error.message)
        }

      case Left(errorMessage) =>
        view.showErrorMessage(errorMessage.message)
    }
  }
}
