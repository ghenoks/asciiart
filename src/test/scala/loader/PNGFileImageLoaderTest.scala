package loader

import helpers.RGBImageHelper
import models.BusinessError
import org.scalatest.funsuite.AnyFunSuite

class PNGFileImageLoaderTest extends AnyFunSuite with RGBImageHelper {
  test("PNGFileImageLoader should accept .png file and instantiate correctly") {

    val filePath = "src/main/scala/resources/heart.png"
    PNGFileImageLoader(filePath) match {
      case Right(loader) =>
        loader.load() match {
          case Right(image) =>
            assert(image.getHeight == 360)
            assert(image.getWidth == 643)
            assert(getRedValue(image, 0, 0) == 255)
            assert(getGreenValue(image, 0, 0) == 255)
            assert(getBlueValue(image, 0, 0) == 255)
          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }

    val filePath2 = "src/main/scala/resources/rocket.png"

    PNGFileImageLoader(filePath2) match {
      case Right(loader2) =>

        loader2.load() match {
          case Right(image) =>
            assert(image.getHeight == 900)
            assert(image.getWidth == 900)
            assert(getRedValue(image, 0, 0) == 255)
            assert(getGreenValue(image, 0, 0) == 255)
            assert(getBlueValue(image, 0, 0) == 255)
          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("PNGFileImageLoader should reject non-.png files") {
    val invalidFileName = "src/main/scala/resources/beetroot.jpg"

    PNGFileImageLoader(invalidFileName) match {
      case Right(loader) => fail("Expected error for non-.png file, but got successful loader")
      case Left(error) =>
        assert(error == BusinessError("PNGFileImageLoader requires .png file"))
    }
  }

  test("Loader should returns error if file path doesn't exist") {
    val filePath = "src/main/scala/resources/invalid.png"

    PNGFileImageLoader(filePath) match {
      case Right(loader) =>
        loader.load() match {
          case Right(image) => fail(s"Unexpected error: file doesn't exist")
          case Left(error) =>
            assert(error == BusinessError("File does not exist."))
        }
      case Left(error) => fail(s"Unexpected error: file doesn't exist")
    }
  }
}
