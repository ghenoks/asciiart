package models.pixel

import models.BusinessError
import models.Pixel.RGBPixel
import org.scalatest.funsuite.AnyFunSuite

class RGBPixelTest extends AnyFunSuite {
  test("RGBPixel creation with valid values") {
    val result = RGBPixel(0, 50, 255)

    result match {
      case Right(pixel) =>
        assert(pixel.getRed == 0)
        assert(pixel.getGreen == 50)
        assert(pixel.getBlue == 255)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("RGBPixel creation with invalid red value") {
    val result = RGBPixel(300, 0, 0)

    result match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
  }

  test("RGBPixel creation with invalid green value") {
    val result = RGBPixel(0, 300, 0)

    result match {
      case Right(pixel) => fail(s"Unexpected error: Green value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
  }

  test("RGBPixel creation with invalid blue value") {
    val result = RGBPixel(0, 0, 300)

    result match {
      case Right(pixel) => fail(s"Unexpected error: Blue value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
  }

  test("RGBPixel creation with all invalid values") {
    val result = RGBPixel(-1, 300, 256)

    result match {
      case Right(pixel) => fail(s"Unexpected error: All color values out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
  }
}
