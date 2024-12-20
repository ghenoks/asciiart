package converter

import helpers.{GreyImageHelper, RGBImageHelper}
import models.Image.{GreyScaleImage, RGBImage}
import models.Pixel.{GreyScalePixel, RGBPixel}
import models.PixelArray
import org.scalatest.funsuite.AnyFunSuite

class RGBToGreyScaleConverterTest extends AnyFunSuite with RGBImageHelper with GreyImageHelper {

  test("Converting RGBImage to GreyScaleImage") {
    val converter = RGBtoGreyScaleConverter()

    val pixel1 = createRGBPixel(0, 50, 200)
    val pixel2 = createRGBPixel(127, 0, 0)
    val pixel3 = createRGBPixel(255, 1, 2)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val image = createRGBImage(pixels)

    converter.convert(image) match {
      case Right(rgbImage) =>
        assert(getGreyValue(rgbImage, 0, 0) == 51)
        assert(getGreyValue(rgbImage, 0, 1) == 38)
        assert(getGreyValue(rgbImage, 0, 2) == 77)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }

    val pixel4 = createRGBPixel(0, 0, 0)
    val pixel5 = createRGBPixel(255, 255, 255)
    val pixel6 = createRGBPixel(255, 127, 0)
    val pixels2 = Vector(Vector(pixel4, pixel5, pixel6))
    val image2 = createRGBImage(pixels2)

    converter.convert(image2) match {
      case Right(rgbImage) =>
        assert(getGreyValue(rgbImage, 0, 0) == 0)
        assert(getGreyValue(rgbImage, 0, 1) == 255)
        assert(getGreyValue(rgbImage, 0, 2) == 151)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}
