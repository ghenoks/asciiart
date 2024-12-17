package models.Pixel

case class GreyScalePixel (private val value: Int) extends Pixel {
  def getValue: Int = value
}
