package Models.Image

import Models.Image.visitor.ImageVisitor
import Models.Pixel.Pixel

trait Image {
  def pixels: Vector[Vector[Pixel]]
  private val height: Int = pixels.length
  private val width: Int = {
    if (pixels.nonEmpty) pixels.head.length
    else 0
  }

  def getHeight: Int = height
  def getWidth: Int = width
  def getPixel (x: Int, y: Int): Pixel
  def getPixels: Vector[Vector[Pixel]]

  def accept[T] (visitor: ImageVisitor[T]): T
}
