package models.Image.visitor

import models.Image.{ASCIIImage, GreyScaleImage, RGBImage}

trait ImageVisitor[T] {
  def visitASCIIImage (image: ASCIIImage): T
  def visitRGBImage (image: RGBImage): T
  def visitGreyScaleImage (image: GreyScaleImage): T
}
