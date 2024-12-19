package models.pixel

import models.Pixel.ASCIIPixel
import org.scalatest.funsuite.AnyFunSuite

class ASCIIPixelTest extends AnyFunSuite {
  test("ASCIIPixel getValue returns correct value") {
    val asciiPixel = ASCIIPixel('A')
    assert(asciiPixel.getValue == 'A')
  }

  test("ASCIIPixel should handle non-printable characters") {
    val asciiPixel = ASCIIPixel('\n')
    assert(asciiPixel.getValue == '\n')
  }
}
