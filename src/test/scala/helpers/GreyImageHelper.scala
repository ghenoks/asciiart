package helpers

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.PixelArray

trait GreyImageHelper {
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

  def getGreyValue(image: GreyScaleImage, x: Int, y: Int): Int = {
    image.getPixel(x, y) match {
      case Right(pixel) => pixel.getValue
      case Left(error) => throw new RuntimeException(s"Failed to get GreyScale value: ${error.message}")
    }
  }
}
