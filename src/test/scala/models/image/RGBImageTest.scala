package models.image

import models.Image.RGBImage
import models.Image.visitor.ImageVisitor
import models.Pixel.RGBPixel
import models.{BusinessError, PixelArray}
import org.scalatest.funsuite.AnyFunSuite
import ui.visitor.TestVisitor

class RGBImageTest extends AnyFunSuite {
  test("RGBImage should retrieve the correct pixel when within bounds") {
    val result = RGBPixel(0, 50, 255)

    result match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) =>

            val image = RGBImage(arr)
            image.getPixel(0, 0) match {
              case Right(pixel) =>
                assert(pixel.getRed == 0)
                assert(pixel.getGreen == 50)
                assert(pixel.getBlue == 255)

              case Left(error) => fail(s"Unexpected error: ${error.message}")
            }
          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("RGBImage returns BusinessError when accessing out-of-bounds pixels") {
    val result = RGBPixel(0, 50, 255)

    result match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) =>
            val image = RGBImage(arr)

            image.getPixel(1, 1) match {
              case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
              case Left(error) => assert(error == BusinessError(s"Pixel at (1, 1) is out of bounds"))
            }
          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("RGBImage should correctly accept a visitor") {
    val result = RGBPixel(0, 50, 255)

    result match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) =>
            val image = RGBImage(arr)
            val visitor = new TestVisitor()
            val result = image.accept(visitor)
            assert(result == "Visited RGBImage")

          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}
