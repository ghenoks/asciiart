package converter

import models.Image.Image

trait Converter[-S <: Image, +T <: Image] {
  def convert(image: S): T
}
