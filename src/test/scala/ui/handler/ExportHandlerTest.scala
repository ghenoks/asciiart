package ui.handler

import exporter.Exporter
import helpers.ASCIIImageHelper
import models.BusinessError
import models.Image.{ASCIIImage, Image}
import models.Pixel.ASCIIPixel
import org.scalatest.funsuite.AnyFunSuite

class ExportHandlerTest extends AnyFunSuite with ASCIIImageHelper {

  class DummyExporter extends Exporter[Image] {
    override def output(item: Image): Either[BusinessError, Unit] = {
      Right(())
    }
  }

  class DummyHandler extends Handler[ASCIIImage] {
    override def handle(item: ASCIIImage): Either[BusinessError, Unit] = {
      Right(())
    }
  }

  test("ExportHandler should export image and pass it to next handler successfully") {
    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    val exporter = new DummyExporter
    val nextHandler = new DummyHandler
    val exportHandler = new ExportHandler(exporter, nextHandler)

    val result = exportHandler.handle(asciiImage)

    assert(result == Right(()))
  }

  test("ExportHandler should return error if exporter fails") {
    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    val exporter = new DummyExporter {
      override def output(item: Image): Either[BusinessError, Unit] =
        Left(BusinessError("Exporter failed"))
    }

    val nextHandler = new DummyHandler
    val exportHandler = new ExportHandler(exporter, nextHandler)

    val result = exportHandler.handle(asciiImage)

    assert(result == Left(BusinessError("Exporter failed")))
  }

  test("ExportHandler should return error if next handler fails") {

    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    val exporter = new DummyExporter
    val handler = new DummyHandler {
      override def handle(item: ASCIIImage): Either[BusinessError, Unit] = Left(BusinessError("Next handler failed"))
    }
    val exportHandler = new ExportHandler(exporter, handler)

    val result = exportHandler.handle(asciiImage)
    assert(result == Left(BusinessError("Next handler failed")))
  }
}
