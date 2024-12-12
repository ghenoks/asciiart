package Filter

import Models.Image.GreyScaleImage
import Models.Pixel.GreyScalePixel

class RotationFilter(val degrees: Int) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {
    degrees match {
      case 90 => rotate(image, (x, y, width, height) => (y, height - 1 - x))
      case 180 => rotate(image, (x, y, width, height) => (height - 1 - x, width - 1 - y))
      case 270 => rotate(image, (x, y, width, height) => (width - 1 - y, x))
      case _ => throw new IllegalArgumentException(s"Unsupported rotation angle: $degrees")
    }
  }

  private def rotate(image: GreyScaleImage, transform: (Int, Int, Int, Int) => (Int, Int)): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth

    val (newHeight, newWidth) = degrees match {
      case 90 | 270 => (width, height)
      case 180 => (height, width)
    }

    val pixels = Array.ofDim[GreyScalePixel](newHeight, newWidth)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        val pixel = image.getPixel(x, y)
        val (newX, newY) = transform(x, y, width, height)
        pixels(newX)(newY) = GreyScalePixel(pixel.getValue)
      }
    }

    val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(vector)
  }
}
