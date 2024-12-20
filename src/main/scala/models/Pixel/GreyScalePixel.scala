package models.Pixel

import models.BusinessError

/*
 * Represents a pixel in GreyScale-Image
 * Only holds grey color value representing its value
 * If value is not between 0-255 returns BusinessError
 */
case class GreyScalePixel private (private val value: Int) extends Pixel {
  def getValue: Int = value
}

object GreyScalePixel {
  def apply(value: Int): Either[BusinessError, GreyScalePixel] = {
    if (value >= 0 && value <= 255) {
      Right(new GreyScalePixel(value))
    }
    else Left(BusinessError("Color values must be between 0 and 255"))
  }
}