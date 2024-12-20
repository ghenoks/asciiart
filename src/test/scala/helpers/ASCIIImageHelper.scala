package helpers

import models.Image.ASCIIImage
import models.Pixel.ASCIIPixel
import models.PixelArray

trait ASCIIImageHelper {
  def createASCIIImage(pixels: Vector[Vector[ASCIIPixel]]): ASCIIImage = {
    val pixelArray = PixelArray(pixels)
    pixelArray match {
      case Right(arr) => ASCIIImage(arr)
      case Left(error) => throw new RuntimeException(s"Failed to create GreyScaleImage: ${error.message}")
    }
  }

  def getASCIIValue(image: ASCIIImage, x: Int, y: Int): Char = {
    image.getPixel(x, y) match {
      case Right(pixel) => pixel.getValue
      case Left(error) => throw new RuntimeException(s"Failed to get ASCIIImage value: ${error.message}")
    }
  }
}
