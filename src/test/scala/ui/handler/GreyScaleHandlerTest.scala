package ui.handler

import converter.GreyScaleConverter
import helpers.{GreyImageHelper, RGBImageHelper}
import models.BusinessError
import models.Image.{GreyScaleImage, RGBImage}
import org.scalatest.funsuite.AnyFunSuite

class GreyScaleHandlerTest extends AnyFunSuite with GreyImageHelper with RGBImageHelper {

  class DummyGreyScaleConverter extends GreyScaleConverter[RGBImage] {
    override def convert(input: RGBImage): Either[BusinessError, GreyScaleImage] = {
      val pixel1 = createGreyScalePixel(0)
      val pixels = Vector(Vector(pixel1))
      Right(createGreyScaleImage(pixels))
    }
  }

  class DummyHandler extends Handler[GreyScaleImage] {
    override def handle(item: GreyScaleImage): Either[BusinessError, Unit] = {
      Right(())
    }
  }

  test("GreyScaleHandler should pass converted GreyScaleImage to next handler successfully") {
    val pixel1 = createRGBPixel(0, 50, 200)
    val pixels = Vector(Vector(pixel1))
    val rgbImage = createRGBImage(pixels)

    val converter = new DummyGreyScaleConverter
    val nextHandler = new DummyHandler
    val greyScaleHandler = new GreyScaleHandler(converter, nextHandler)

    val result = greyScaleHandler.handle(rgbImage)

    assert(result == Right(()))
  }

  test("GreyScaleHandler should return error if conversion fails") {
    val pixel1 = createRGBPixel(0, 50, 200)
    val pixels = Vector(Vector(pixel1))
    val rgbImage = createRGBImage(pixels)

    val converter = new DummyGreyScaleConverter {
      override def convert(input: RGBImage): Either[BusinessError, GreyScaleImage] =
        Left(BusinessError("Conversion failed"))
    }
    val nextHandler = new DummyHandler
    val greyScaleHandler = new GreyScaleHandler(converter, nextHandler)

    val result = greyScaleHandler.handle(rgbImage)

    assert(result == Left(BusinessError("Conversion failed")),
      "Expected an error when the conversion fails")
  }

  test("GreyScaleHandler should return error if next handler fails") {

    val pixel1 = createRGBPixel(0, 50, 200)
    val pixels = Vector(Vector(pixel1))
    val rgbImage = createRGBImage(pixels)

    val converter = new DummyGreyScaleConverter
    val handler = new DummyHandler {
      override def handle(item: GreyScaleImage): Either[BusinessError, Unit] = Left(BusinessError("Next handler failed"))
    }
    val greyHandler = new GreyScaleHandler(converter, handler)

    val result = greyHandler.handle(rgbImage)
    assert(result == Left(BusinessError("Next handler failed")))
  }
}
