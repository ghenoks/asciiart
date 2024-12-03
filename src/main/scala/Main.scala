package Main

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

@main def main (args: String*): Unit = {
  println("Hello there")
  val file = args(0)

  val image: BufferedImage = ImageIO.read(new File(file));
  println(s"Width: ${image.getWidth}")
  println(s"Height: ${image.getHeight}")
  println(s"Image Type: ${image.getType}")
  // load image
  // resize image
  // convert to grayscale
  // map grayscale values to ASCII characters
  // generate ASCII art
  // apply filters
  // (lower font size, increase density)

  // Regex na pattern matching
}
