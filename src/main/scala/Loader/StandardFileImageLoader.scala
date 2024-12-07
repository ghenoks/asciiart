package Loader

import Models.Image.Image
import Models.Pixel.Pixel

class StandardFileImageLoader (fileName: String) extends FileImageLoader(fileName) {
  override def load(): Image = {
    val pixels = Vector(
      Vector(Pixel(), Pixel()), // First row: Red, Green
      Vector(Pixel(), Pixel()))
    val image = Image(1, 2, pixels)
    image
  }
}
