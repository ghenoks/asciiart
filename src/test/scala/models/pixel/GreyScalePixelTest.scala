package models.pixel

import models.BusinessError
import models.Pixel.GreyScalePixel
import org.scalatest.funsuite.AnyFunSuite

class GreyScalePixelTest extends AnyFunSuite {
  test("GreyScalePixel creation with valid value") {
    val result = GreyScalePixel(255)

    result match {
      case Right(pixel) => assert(pixel.getValue == 255)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("GreyScalePixel creation with invalid value") {
    val result = GreyScalePixel(300)

    result match {
      case Right(pixel) => fail(s"Unexpected error: Grey value should be of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
  }
}
