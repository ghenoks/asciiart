package models.pixel

import models.BusinessError
import models.Pixel.GreyScalePixel
import org.scalatest.funsuite.AnyFunSuite

class GreyScalePixelTest extends AnyFunSuite {
  test("GreyScalePixel creation with valid value") {
    val result1 = GreyScalePixel(150)
    val result2 = GreyScalePixel(255)
    val result3 = GreyScalePixel(0)

    result1 match {
      case Right(pixel) => assert(pixel.getValue == 150)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    result2 match {
      case Right(pixel) => assert(pixel.getValue == 255)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    result3 match {
      case Right(pixel) => assert(pixel.getValue == 0)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("GreyScalePixel creation with invalid value") {
    val result1 = GreyScalePixel(300)
    val result2 = GreyScalePixel(256)
    val result3 = GreyScalePixel(-1)

    result1 match {
      case Right(pixel) => fail(s"Unexpected error: Grey value should be of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
    result2 match {
      case Right(pixel) => fail(s"Unexpected error: Grey value should be of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
    result3 match {
      case Right(pixel) => fail(s"Unexpected error: Grey value should be of bounds")
      case Left(error) => assert(error == BusinessError("Color values must be between 0 and 255"))
    }
  }
}
