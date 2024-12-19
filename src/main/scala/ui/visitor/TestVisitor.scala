package ui.visitor

import models.Image.{ASCIIImage, GreyScaleImage, RGBImage}
import models.Image.visitor.ImageVisitor

class TestVisitor extends ImageVisitor[String] {
  override def visitASCIIImage(image: ASCIIImage): String = "Visited ASCIIImage"
  override def visitRGBImage(rgbImage: RGBImage): String = "Visited RGBImage"
  override def visitGreyScaleImage(image: GreyScaleImage): String = "Visited GreyScaleImage"
}
