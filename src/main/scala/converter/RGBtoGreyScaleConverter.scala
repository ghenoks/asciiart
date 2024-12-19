package converter

import models.Image.{GreyScaleImage, RGBImage}
import models.Pixel.{GreyScalePixel, RGBPixel}
import models.PixelArray

import scala.collection.mutable.ArrayBuffer

class RGBtoGreyScaleConverter extends GreyScaleConverter[RGBImage] {
  override def convert(image: RGBImage): GreyScaleImage = {

    val pixels = ArrayBuffer[ArrayBuffer[GreyScalePixel]]()

    val height: Int = image.getHeight
    val width: Int = image.getWidth

    for (x <- 0 until height) {
      val pixelLine = ArrayBuffer[GreyScalePixel]()
      for (y <- 0 until width) {
        image.getPixel(x, y) match {
          case Right(rgbPixel) =>
            val red = rgbPixel.getRed
            val green = rgbPixel.getGreen
            val blue = rgbPixel.getBlue

            val value: Int = ((0.3 * red) + (0.59 * green) + (0.11 * blue)).toInt
            GreyScalePixel(value) match {
              case Right(pixel) => pixelLine.addOne(pixel)
              case Left(error) => throw IllegalArgumentException(error.message)
            }
          case Left(error) => throw IllegalArgumentException(error.message)
        }


      }
      pixels.addOne(pixelLine)
    }

    val vector = pixels.map(_.toVector).toVector
    val pixelArray = PixelArray[GreyScalePixel](vector)

    pixelArray match {
      case Right(arr) => GreyScaleImage(arr)
      case Left(error) => throw IllegalArgumentException(error.message)
    }

  }
}
