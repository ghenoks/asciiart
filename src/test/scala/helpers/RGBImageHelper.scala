package helpers

import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.PixelArray

/*
 * Used to help with checking if RGB-Image and RGB-Pixel creation or get colors went without problem
 * else throws exception
 */
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

  def getRedValue(image: RGBImage, x: Int, y: Int): Int = {
    image.getPixel(x, y) match {
      case Right(pixel) => pixel.getRed
      case Left(error) => throw new RuntimeException(s"Failed to get GreyScale value: ${error.message}")
    }
  }

  def getGreenValue(image: RGBImage, x: Int, y: Int): Int = {
    image.getPixel(x, y) match {
      case Right(pixel) => pixel.getGreen
      case Left(error) => throw new RuntimeException(s"Failed to get GreyScale value: ${error.message}")
    }
  }

  def getBlueValue(image: RGBImage, x: Int, y: Int): Int = {
    image.getPixel(x, y) match {
      case Right(pixel) => pixel.getBlue
      case Left(error) => throw new RuntimeException(s"Failed to get GreyScale value: ${error.message}")
    }
  }
}
