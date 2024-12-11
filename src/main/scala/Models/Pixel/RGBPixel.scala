package Models.Pixel

case class RGBPixel  (private val red: Int, private val green: Int, private val blue: Int) extends Pixel {
  def getRed: Int = red

  def getGreen: Int = green

  def getBlue: Int = blue
}

