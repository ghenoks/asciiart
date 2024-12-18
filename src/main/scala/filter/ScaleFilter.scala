package filter

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.PixelArray

class ScaleFilter(val scale: Float) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {

    scale match {
      case 4 => scale4(image)
      case 0.25 => scale025(image)
    }
  }

  private def scale4(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height * 2, width * 2)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        val value = image.getPixel(x, y).getValue
        pixels(x * 2)(y * 2) = GreyScalePixel(value)
        pixels(x * 2 + 1)(y * 2) = GreyScalePixel(value)
        pixels(x * 2)(y * 2 + 1) = GreyScalePixel(value)
        pixels(x * 2 + 1)(y * 2 + 1) = GreyScalePixel(value)
      }
    }

    val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(PixelArray[GreyScalePixel](vector))
  }

  private def scale025(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height/2, width/2)

    for (x <- 0 until height/2) {
      for (y <- 0 until width/2) {
        val value = image.getPixel(x * 2, y * 2).getValue
        pixels (x)(y) = GreyScalePixel(value)
      }
    }

    val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(PixelArray[GreyScalePixel](vector))
  }
}
