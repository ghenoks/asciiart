package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.Pixel
import models.{BusinessError, PixelArray}

trait Image {

  def pixelArray: PixelArray[Pixel]

  private val height: Int = pixelArray.getHeight
  private val width: Int = pixelArray.getWidth
  def getHeight: Int = height
  def getWidth: Int = width

  def getPixel(x: Int, y: Int): Either[BusinessError, Pixel]

  def accept[T](visitor: ImageVisitor[T]): T
}
