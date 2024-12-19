package models.conversionTable

import models.Pixel.GreyScalePixel
import org.scalatest.funsuite.AnyFunSuite

class NonLinearConversionTableTest extends AnyFunSuite {
  test("NonLinearConversionTable should return the correct symbol based on the pixel value") {
    val table = "@%#*+=-:. "
    val conversionTable = new NonLinearConversionTable(table)

    GreyScalePixel(0) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '@')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(99) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '@')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(100) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '%')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(116) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '%')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(117) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '#')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(255) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == ' ')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }
  }

  test("NonLinearConversionTable with only 1 symbol") {
    val table = "#"
    val conversionTable = new NonLinearConversionTable(table)

    GreyScalePixel(0) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '#')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(127) match {
      case Right(pixel) =>
        println(conversionTable.getSymbol(pixel))
        assert(conversionTable.getSymbol(pixel) == '#')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(255) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '#')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }
  }

  test("NonLinearConversionTable with empty table") {
    val table = ""
    val conversionTable = new NonLinearConversionTable(table)

    GreyScalePixel(0) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == ' ')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(127) match {
      case Right(pixel) =>
        println(conversionTable.getSymbol(pixel))
        assert(conversionTable.getSymbol(pixel) == ' ')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(255) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == ' ')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }
  }

  test("NonLinearConversionTable with too big table (bigger then 256 symbols)") {
    val table = "..$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ....................$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ..............................................................................................................................................................................."
    val conversionTable = new NonLinearConversionTable(table)

    GreyScalePixel(0) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == ' ')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(127) match {
      case Right(pixel) =>
        println(conversionTable.getSymbol(pixel))
        assert(conversionTable.getSymbol(pixel) == ' ')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(255) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == ' ')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }
  }
}
