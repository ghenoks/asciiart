package helpers

import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.PixelArray

trait RGBImageHelper {
  def createRGBPixel(red: Int, green: Int, blue: Int): RGBPixel = {
    RGBPixel(red, green, blue) match {
      case Right(pixel) => pixel
      case Left(error) => throw new RuntimeException(s"Failed to create RGBPixel: ${error.message}")
    }
  }

  def createRGBImage(pixels: Vector[Vector[RGBPixel]]): RGBImage = {
    val pixelArray = PixelArray(pixels)
    pixelArray match {
      case Right(arr) => RGBImage(arr)
      case Left(error) => throw new RuntimeException(s"Failed to create RGBImage: ${error.message}")
    }
  }
}
