package ui.handler

import helpers.{GreyImageHelper, RGBImageHelper}
import models.BusinessError
import models.Image.RGBImage
import org.scalatest.funsuite.AnyFunSuite

class ImageToRGBHandlerTest extends AnyFunSuite with RGBImageHelper with GreyImageHelper {

  class DummyHandler extends Handler[RGBImage] {
    override def handle(item: RGBImage): Either[BusinessError, Unit] = {
      Right(())
    }
  }

  test("ImageToRGBHandler should pass RGBImage to next handler successfully") {
    val pixel1 = createRGBPixel(0, 50, 200)
    val pixels = Vector(Vector(pixel1))
    val rgbImage = createRGBImage(pixels)

    val nextHandler = new DummyHandler
    val imageToRGBHandler = new ImageToRGBHandler(nextHandler)

    val result = imageToRGBHandler.handle(rgbImage)

    assert(result == Right(()))
  }

  test("ImageToRGBHandler should return error if image is not RGB") {
    val pixel1 = createGreyScalePixel(0)
    val pixels = Vector(Vector(pixel1))
    val greyScaleImage = createGreyScaleImage(pixels)

    val nextHandler = new DummyHandler
    val imageToRGBHandler = new ImageToRGBHandler(nextHandler)

    val result = imageToRGBHandler.handle(greyScaleImage)

    assert(result == Left(BusinessError("Needed RGBImage, got a different type")))
  }

  test("ImageToRGBHandler should return error if next handler fails") {

    val pixel1 = createRGBPixel(0, 50, 200)
    val pixels = Vector(Vector(pixel1))
    val rgbImage = createRGBImage(pixels)

    val handler = new DummyHandler {
      override def handle(item: RGBImage): Either[BusinessError, Unit] = Left(BusinessError("Next handler failed"))
    }
    val imageToRGBHandler = new ImageToRGBHandler(handler)

    val result = imageToRGBHandler.handle(rgbImage)
    assert(result == Left(BusinessError("Next handler failed")))
  }
}
