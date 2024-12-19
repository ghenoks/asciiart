package models.pixel

import models.BusinessError
import models.Pixel.RGBPixel
import org.scalatest.funsuite.AnyFunSuite

class RGBPixelTest extends AnyFunSuite {
  test("RGBPixel creation with valid values") {
    val result1 = RGBPixel(255, 255, 255)
    val result2 = RGBPixel(0, 50, 255)
    val result3 = RGBPixel(0, 0, 0)

    result1 match {
      case Right(pixel) =>
        assert(pixel.getRed == 255)
        assert(pixel.getGreen == 255)
        assert(pixel.getBlue == 255)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    result2 match {
      case Right(pixel) =>
        assert(pixel.getRed == 0)
        assert(pixel.getGreen == 50)
        assert(pixel.getBlue == 255)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    result3 match {
      case Right(pixel) =>
        assert(pixel.getRed == 0)
        assert(pixel.getGreen == 0)
        assert(pixel.getBlue == 0)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("RGBPixel creation with invalid red value") {
    val result1 = RGBPixel(300, 0, 0)
    val result2 = RGBPixel(256, 0, 0)
    val result3 = RGBPixel(-1, 0, 0)

    result1 match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
    result2 match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
    result3 match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
  }

  test("RGBPixel creation with invalid green value") {
    val result1 = RGBPixel(0, 300, 0)
    val result2 = RGBPixel(0, -1, 0)
    val result3 = RGBPixel(0, 256, 0)

    result1 match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
    result2 match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
    result3 match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
  }

  test("RGBPixel creation with invalid blue value") {
    val result1 = RGBPixel(0, 0, 300)
    val result2 = RGBPixel(0, 0, 256)
    val result3 = RGBPixel(0, 0, -1)

    result1 match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
    result2 match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
    result3 match {
      case Right(pixel) => fail(s"Unexpected error: Red value out of bounds")
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
