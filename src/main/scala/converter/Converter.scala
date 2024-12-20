package converter

import models.BusinessError
/*
 * Converts an item to a different item (or the same)
 * If it fails to convert item it returns BusinessError
 * @param image - item to convert 
 */
trait Converter[-S, +T] {
  def convert(image: S): Either[BusinessError, T]
}
