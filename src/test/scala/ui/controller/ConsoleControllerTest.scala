package ui.controller

import converter.{GreyScaleToASCIIConverter, RGBtoGreyScaleConverter}
import filter.{InversionFilter, MixedImageFilter, RotationFilter}
import loader.JPGFileImageLoader
import models.conversionTable.PaulBourkeTable
import models.{Argument, BusinessError, ModuleHolder}
import org.scalatest.funsuite.AnyFunSuite
import org.mockito.Mockito.*
import org.mockito.ArgumentMatchers.*
import ui.argumentParser.{ArgumentParser, MyArgumentParser}
import ui.handler.{ASCIIHandler, EndHandler, ExportHandler, FilterHandler, GreyScaleHandler, ImageToRGBHandler, LoadHandler}
import ui.moduleGetter.MyModuleGetter
import ui.view.{ConsoleView, View}

class ConsoleControllerTest extends AnyFunSuite {
  test("ConsoleController should process commands and show success message") {

    val view = new ConsoleView()

    val args = List(
      "--image", "src/main/scala/resources/beetroot.jpg",
      "--rotate", "+90",
      "--invert",
      "--output-file", "src/main/scala/resources/result.txt",
    )

    val argumentParser = new MyArgumentParser(args)
    val controller = new ConsoleController(argumentParser, view)

    controller.run()

    assert(view.successMessageShown)
    assert(!view.errorMessageShown)
  }

  test("ConsoleController should show error message on failure") {
    val view = new ConsoleView()

    val args = List(
      "--rotate", "+90",
      "--invert",
      "--output-file", "src/main/scala/resources/result.txt",
    )

    val argumentParser = new MyArgumentParser(args)
    val controller = new ConsoleController(argumentParser, view)

    controller.run()

    assert(!view.successMessageShown)
    assert(view.errorMessageShown)
  }
}
