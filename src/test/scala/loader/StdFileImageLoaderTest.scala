package loader

import helpers.RGBImageHelper
import models.BusinessError
import org.scalatest.funsuite.AnyFunSuite

class StdFileImageLoaderTest extends AnyFunSuite with RGBImageHelper {
  test("Loader should return RGBImage when given file path is valid") {
    val filePath1 = "src/main/scala/resources/pikachu.jpg"
    val loader1 = new StdFileImageLoader(filePath1)

    loader1.load() match {
      case Right(image) =>
        assert(image.getHeight == 775)
        assert(image.getWidth == 736)
        assert(getRedValue(image, 0, 0) == 247)
        assert(getGreenValue(image, 0, 0) == 247)
        assert(getBlueValue(image, 0, 0) == 247)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }

    val filePath2 = "src/main/scala/resources/beetroot.jpg"
    val loader2 = new StdFileImageLoader(filePath2)

    loader2.load() match {
      case Right(image) =>
        assert(image.getHeight == 360)
        assert(image.getWidth == 360)
        assert(getRedValue(image, 0, 0) == 255)
        assert(getGreenValue(image, 0, 0) == 255)
        assert(getBlueValue(image, 0, 0) == 255)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Loader should return error when given file path is not valid format") {
    val filePath = "src/main/scala/resources/result.txt"
    val loader = new StdFileImageLoader(filePath)

    loader.load() match {
      case Right(image) => fail(s"Unexpected error: file doesn't exist")
      case Left(error) =>
        assert(error == BusinessError("Not a supported file format."))
    }
  }

  test("Loader should return exception if file path doesn't exist") {
    val filePath = "src/main/scala/resources/invalid.jpg"
    val loader = new StdFileImageLoader(filePath)

    loader.load() match {
      case Right(image) => fail(s"Unexpected error: file doesn't exist")
      case Left(error) =>
        assert(error == BusinessError("File does not exist."))
    }
  }
}
