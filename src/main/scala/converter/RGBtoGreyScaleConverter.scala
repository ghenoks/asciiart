package converter

import models.Image.{GreyScaleImage, RGBImage}
import models.Pixel.{GreyScalePixel, RGBPixel}

import scala.collection.mutable.ArrayBuffer

class RGBtoGreyScaleConverter extends GreyScaleConverter[RGBImage] {
  override def convert(image: RGBImage): GreyScaleImage = {

    val pixels = ArrayBuffer[ArrayBuffer[GreyScalePixel]]()

    val height: Int = image.getHeight
    val width: Int = image.getWidth

    for (x <- 0 until height) {
      val pixelLine = ArrayBuffer[GreyScalePixel]()
      for (y <- 0 until width) {
        val rgbPixel: RGBPixel = image.getPixel(x, y)

        val red = rgbPixel.getRed
        val green = rgbPixel.getGreen
        val blue = rgbPixel.getBlue

        val value: Int = ((0.3 * red) + (0.59 * green) + (0.11 * blue)).toInt
        pixelLine.addOne(GreyScalePixel(value))
      }
      pixels.addOne(pixelLine)
    }

    val vector: Vector[Vector[GreyScalePixel]] = pixels.map(_.toVector).toVector
    GreyScaleImage(vector)
  }
}
