package ui.handler

import helpers.GreyImageHelper
import loader.{ImageLoader, TestImageLoader}
import models.BusinessError
import models.Image.{GreyScaleImage, Image}
import org.scalatest.funsuite.AnyFunSuite

class LoadHandlerTest extends AnyFunSuite {

  class StubImageLoader extends ImageLoader with GreyImageHelper {
    override def load(): Either[BusinessError, Image] = {
      val pixel1 = createGreyScalePixel(0)
      val pixels = Vector(Vector(pixel1))
      Right(createGreyScaleImage(pixels))
    }
  }

  class DummyHandler extends Handler[Image] {
    override def handle(item: Image): Either[BusinessError, Unit] = {
      Right(())
    }
  }

  test("LoadHandler should pass loaded image to next handler successfully") {

    val loader = new StubImageLoader
    val nextHandler = DummyHandler()

    val loadHandler = new LoadHandler(loader, nextHandler)

    val result = loadHandler.handle("")
    assert(result == Right(()))
  }

  test("LoadHandler should return error if loader fails") {

    val loader = new StubImageLoader {
      override def load(): Either[BusinessError, Image] = Left(BusinessError("Loader failed"))
    }
    val handler = new DummyHandler
    val loadHandler = new LoadHandler(loader, handler)

    val result = loadHandler.handle(())
    assert(result == Left(BusinessError("Loader failed")))
  }

  test("LoadHandler should return error if next handler fails") {

    val loader = new StubImageLoader
    val handler = new DummyHandler {
      override def handle(item: Image): Either[BusinessError, Unit] = Left(BusinessError("Next handler failed"))
    }
    val loadHandler = new LoadHandler(loader, handler)

    val result = loadHandler.handle(())
    assert(result == Left(BusinessError("Next handler failed")))
  }
}
