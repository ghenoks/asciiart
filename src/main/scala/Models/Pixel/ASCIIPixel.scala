package Models.Pixel

case class ASCIIPixel(private val value: Char) extends Pixel {
  def getValue: Char = value
}
