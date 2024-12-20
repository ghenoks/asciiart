package ui.handler

import filter.ImageFilter
import helpers.GreyImageHelper
import models.BusinessError
import models.Image.GreyScaleImage
import org.scalatest.funsuite.AnyFunSuite

class FilterHandlerTest extends AnyFunSuite with GreyImageHelper {
  class DummyFilter extends ImageFilter[GreyScaleImage] {
    override def applyFilter(input: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {
      Right(input)
    }
  }

  class DummyHandler extends Handler[GreyScaleImage] {
    override def handle(item: GreyScaleImage): Either[BusinessError, Unit] = {
      Right(())
    }
  }

  test("GreyScaleHandler should pass converted GreyScaleImage to next handler successfully") {
    val pixel1 = createGreyScalePixel(0)
    val pixels = Vector(Vector(pixel1))
    val greyScaleImage = createGreyScaleImage(pixels)

    val filter = new DummyFilter
    val nextHandler = new DummyHandler
    val filterHandler = new FilterHandler(filter, nextHandler)

    val result = filterHandler.handle(greyScaleImage)

    assert(result == Right(()))
  }

  test("GreyScaleHandler should return error if conversion fails") {
    val pixel1 = createGreyScalePixel(0)
    val pixels = Vector(Vector(pixel1))
    val greyScaleImage = createGreyScaleImage(pixels)

    val filter = new DummyFilter {
      override def applyFilter(input: GreyScaleImage): Either[BusinessError, GreyScaleImage] =
        Left(BusinessError("Filter failed"))
    }
    val nextHandler = new DummyHandler
    val filterHandler = new FilterHandler(filter, nextHandler)

    val result = filterHandler.handle(greyScaleImage)

    assert(result == Left(BusinessError("Filter failed")))
  }

  test("GreyScaleHandler should return error if next handler fails") {

    val pixel1 = createGreyScalePixel(0)
    val pixels = Vector(Vector(pixel1))
    val greyScaleImage = createGreyScaleImage(pixels)

    val filter = new DummyFilter
    val handler = new DummyHandler {
      override def handle(item: GreyScaleImage): Either[BusinessError, Unit] = Left(BusinessError("Next handler failed"))
    }
    val filterHandler = new FilterHandler(filter, handler)

    val result = filterHandler.handle(greyScaleImage)
    assert(result == Left(BusinessError("Next handler failed")))
  }
}
