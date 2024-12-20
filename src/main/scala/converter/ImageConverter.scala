package converter

import models.BusinessError
import models.Image.Image
/*
 * Converts one Image to another Image
 * Returns BusinessError if conversion fails
 */
trait ImageConverter[-S <: Image, +T <: Image] extends Converter[S, T] {
  def convert(image: S): Either[BusinessError, T]
}
