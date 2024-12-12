package Filter

import Models.Image.GreyScaleImage
import Models.Pixel.GreyScalePixel

class RotationFilter(val degrees: Int) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {

    degrees match {
      case 90 => rotate90(image)
      case 180 => rotate180(image)
      case 270 => rotate270(image)
    }
  }

  private def rotate90(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](width, height)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        val pixel = image.getPixel(x, y)
        pixels(y)(height - 1 - x) = GreyScalePixel(pixel.getValue)
      }
    }

    val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(vector)
  }

  private def rotate180(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height, width)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        val pixel = image.getPixel(x, y)
        pixels(height - 1 - x)(width - 1 - y) = GreyScalePixel(pixel.getValue)
      }
    }

    val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(vector)
  }

  private def rotate270(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](width, height)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        val pixel = image.getPixel(x, y)
        pixels(width - 1 - y)(x) = GreyScalePixel(pixel.getValue)
      }
    }

    val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(vector)
  }
}
