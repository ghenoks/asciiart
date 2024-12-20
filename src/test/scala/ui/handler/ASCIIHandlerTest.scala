package ui.handler

import converter.ASCIIConverter
import helpers.{ASCIIImageHelper, GreyImageHelper}
import models.BusinessError
import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.{ASCIIPixel, GreyScalePixel}
import models.conversionTable.{ConversionTable, PaulBourkeTable}
import org.scalatest.funsuite.AnyFunSuite

class ASCIIHandlerTest extends AnyFunSuite with GreyImageHelper with ASCIIImageHelper {

  case class DummyConverter(table: ConversionTable[GreyScalePixel]) extends ASCIIConverter[GreyScaleImage, GreyScalePixel] {
    override def convert(item: GreyScaleImage): Either[BusinessError, ASCIIImage] = {

      val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
      Right(createASCIIImage(pixels))
    }
  }

  class DummyHandler extends Handler[ASCIIImage] {
    override def handle(item: ASCIIImage): Either[BusinessError, Unit] = {
      Right(())
    }
  }

  test("ASCIIHandler should convert GreyScaleImage to ASCIIImage and pass it to next handler successfully") {
    val pixel1 = createGreyScalePixel(0)
    val pixels = Vector(Vector(pixel1))
    val greyScaleImage = createGreyScaleImage(pixels)

    val converter = DummyConverter(PaulBourkeTable())
    val nextHandler = new DummyHandler
    val asciiHandler = new ASCIIHandler(converter, nextHandler)

    val result = asciiHandler.handle(greyScaleImage)

    assert(result == Right(()))
  }

  test("ASCIIHandler should return error if converter fails") {
    val pixel1 = createGreyScalePixel(0)
    val pixels = Vector(Vector(pixel1))
    val greyScaleImage = createGreyScaleImage(pixels)

    val converter = new DummyConverter(PaulBourkeTable()) {
      override def convert(item: GreyScaleImage): Either[BusinessError, ASCIIImage] =
        Left(BusinessError("Converter failed"))
    }

    val nextHandler = new DummyHandler
    val asciiHandler = new ASCIIHandler(converter, nextHandler)

    val result = asciiHandler.handle(greyScaleImage)

    assert(result == Left(BusinessError("Converter failed")))
  }

  test("ASCIIHandler should return error if next handler fails") {

    val pixel1 = createGreyScalePixel(0)
    val pixels = Vector(Vector(pixel1))
    val greyScaleImage = createGreyScaleImage(pixels)

    val converter = DummyConverter(PaulBourkeTable())
    val handler = new DummyHandler {
      override def handle(item: ASCIIImage): Either[BusinessError, Unit] = Left(BusinessError("Next handler failed"))
    }
    val asciiHandler = new ASCIIHandler(converter, handler)

    val result = asciiHandler.handle(greyScaleImage)
    assert(result == Left(BusinessError("Next handler failed")))
  }
}
