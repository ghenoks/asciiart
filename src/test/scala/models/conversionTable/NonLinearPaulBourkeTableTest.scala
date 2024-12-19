package models.conversionTable

import models.Pixel.GreyScalePixel
import org.scalatest.funsuite.AnyFunSuite

class NonLinearPaulBourkeTableTest extends AnyFunSuite {
  test("NonLinear - PaulBourke should return the correct symbol based on the pixel value") {
    val conversionTable = new NonLinearPaulBourkeTable

    GreyScalePixel(0) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '$')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(100) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '$')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(101) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '@')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(127) match {
      case Right(pixel) =>
        println(conversionTable.getSymbol(pixel))
        assert(conversionTable.getSymbol(pixel) == 'b')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(255) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == ' ')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }
  }
}
