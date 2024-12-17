package filter

import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.{ASCIIPixel, GreyScalePixel}

class FlipImageFilter(val flipValue: Char) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {
    flipValue match {
      case 'x' => flipHorizontal(image)
      case 'y' => flipVertical(image)
    }
  }

  private def flipHorizontal(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth

    val pixels = Array.ofDim[GreyScalePixel](height, width)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        val pixel = image.getPixel(x, y)
        pixels(height - 1 - x)(y) = GreyScalePixel(pixel.getValue)
      }
    }
    val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(vector)
  }

  private def flipVertical(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth

    val pixels = Array.ofDim[GreyScalePixel](height, width)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        val pixel = image.getPixel(x, y)
        pixels(x)(width - 1 - y) = GreyScalePixel(pixel.getValue)
      }
    }
    val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(vector)
  }
}
