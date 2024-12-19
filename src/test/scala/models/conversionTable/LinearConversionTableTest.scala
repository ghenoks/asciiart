package models.conversionTable

import models.Pixel.GreyScalePixel
import org.scalatest.funsuite.AnyFunSuite

class LinearConversionTableTest extends AnyFunSuite {
  test("LinearConversionTable should return the correct symbol based on the pixel value") {
    val table = "@%#*+=-:. "
    val conversionTable = new LinearConversionTable(table)

    GreyScalePixel(0) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == '@')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(127) match {
      case Right(pixel) =>
        println(conversionTable.getSymbol(pixel))
        assert(conversionTable.getSymbol(pixel) == '=')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }

    GreyScalePixel(255) match {
      case Right(pixel) =>
        assert(conversionTable.getSymbol(pixel) == ' ')
      case Left(error) => fail(s"Unexpected BusinessError: ${error.message}")
    }
  }

  test("LinearConversionTable with only 1 symbol") {
    val table = "#"
    val conversionTable = new LinearConversionTable(table)

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

  test("LinearConversionTable with empty table") {
    val table = ""
    val conversionTable = new LinearConversionTable(table)

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

  test("LinearConversionTable with too big table (bigger then 256 symbols)") {
    val table = "..$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ....................$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ..............................................................................................................................................................................."
    val conversionTable = new LinearConversionTable(table)

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
