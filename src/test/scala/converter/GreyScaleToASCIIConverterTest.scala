package converter

import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.GreyScalePixel
import models.{BusinessError, PixelArray}
import models.conversionTable.PaulBourkeTable
import org.scalatest.funsuite.AnyFunSuite

class GreyScaleToASCIIConverterTest extends AnyFunSuite {

  def createGreyScalePixel(value: Int): GreyScalePixel = {
    GreyScalePixel(value) match {
      case Right(pixel) => pixel
      case Left(error) => throw new RuntimeException(s"Failed to create GreyScalePixel: ${error.message}")
    }
  }

  def createGreyScaleImage(pixels: Vector[Vector[GreyScalePixel]]): GreyScaleImage = {
    val pixelArray = PixelArray(pixels)
    pixelArray match {
      case Right(arr) => GreyScaleImage(arr)
      case Left(error) => throw new RuntimeException(s"Failed to create GreyScaleImage: ${error.message}")
    }
  }

  def getASCIIValue (image: ASCIIImage, x: Int, y: Int): Char = {
    image.getPixel(x, y) match {
      case Right(pixel) => pixel.getValue
      case Left(error) => throw new RuntimeException(s"Failed to get ASCIIImage value: ${error.message}")
    }
  }

  test("Converting GreyScaleImage to correct ASCIIImage") {
    val converter = GreyScaleToASCIIConverter(PaulBourkeTable())

    val pixel1 = createGreyScalePixel(0)
    val pixel2 = createGreyScalePixel(127)
    val pixel3 = createGreyScalePixel(255)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val greyScaleImage = createGreyScaleImage(pixels)

    converter.convert(greyScaleImage) match {
      case Right(asciiImage) =>
        assert(getASCIIValue(asciiImage, 0, 0) == '$')
        assert(getASCIIValue(asciiImage, 0, 1) == '|')
        assert(getASCIIValue(asciiImage, 0, 2) == ' ')
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }

    val pixel4 = createGreyScalePixel(15)
    val pixel5 = createGreyScalePixel(60)
    val pixel6 = createGreyScalePixel(180)
    val pixels2 = Vector(Vector(pixel4, pixel5, pixel6))
    val greyScaleImage2 = createGreyScaleImage(pixels2)

    converter.convert(greyScaleImage2) match {
      case Right(asciiImage) =>
        assert(getASCIIValue(asciiImage, 0, 0) == '&')
        assert(getASCIIValue(asciiImage, 0, 1) == 'Z')
        assert(getASCIIValue(asciiImage, 0, 2) == 'I')
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}
