package models.Pixel

import models.BusinessError

case class RGBPixel private (private val red: Int, private val green: Int, private val blue: Int) extends Pixel {
  def getRed: Int = red
  def getGreen: Int = green
  def getBlue: Int = blue
}

object RGBPixel {
  def apply(red: Int, green: Int, blue: Int): Either[BusinessError, RGBPixel] = {
    if (red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255) {
      Right(new RGBPixel(red, green, blue))
    }
    else Left(BusinessError("Color values must be between 0 and 255"))
  }
}
