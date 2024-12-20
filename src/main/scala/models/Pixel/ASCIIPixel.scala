package models.Pixel

/*
 * Represents a pixel in ASCII-Image
 * Only holds char representing its value 
 */
case class ASCIIPixel(private val value: Char) extends Pixel {
  def getValue: Char = value
}
