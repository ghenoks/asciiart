package Models.Pixel

case class GreyScalePixel (private val value: Double) extends Pixel {
  def getValue: Double = value
}
