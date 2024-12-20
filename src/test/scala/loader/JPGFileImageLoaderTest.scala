package loader

import helpers.RGBImageHelper
import models.BusinessError
import org.scalatest.funsuite.AnyFunSuite

class JPGFileImageLoaderTest extends AnyFunSuite with RGBImageHelper {
  test("JPGFileImageLoader should accept .jpg file and instantiate correctly") {
    val filePath = "src/main/scala/resources/pikachu.jpg"
    JPGFileImageLoader(filePath) match {
      case Right(loader) =>
        loader.load() match {
          case Right(image) =>
            assert(image.getHeight == 775)
            assert(image.getWidth == 736)
            assert(getRedValue(image, 0, 0) == 247)
            assert(getGreenValue(image, 0, 0) == 247)
            assert(getBlueValue(image, 0, 0) == 247)
          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }

    val filePath2 = "src/main/scala/resources/beetroot.jpg"
    JPGFileImageLoader(filePath2) match {
      case Right(loader2) =>

        loader2.load() match {
          case Right(image) =>
            assert(image.getHeight == 360)
            assert(image.getWidth == 360)
            assert(getRedValue(image, 0, 0) == 255)
            assert(getGreenValue(image, 0, 0) == 255)
            assert(getBlueValue(image, 0, 0) == 255)
          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("JPGFileImageLoader should reject non-.jpg files") {
    val invalidFileName = "src/main/scala/resources/heart.png"

    JPGFileImageLoader(invalidFileName) match {
      case Right(loader) => fail("Expected error for non-.jpg file, but got successful loader")
      case Left(error) =>
        assert(error == BusinessError("JPGFileImageLoader requires .jpg file"))
    }
  }

  test("Loader should returns error if file path doesn't exist") {
    val filePath = "src/main/scala/resources/invalid.jpg"

    JPGFileImageLoader(filePath) match {
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
