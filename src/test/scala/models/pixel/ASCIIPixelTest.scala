package models.pixel

import models.Pixel.ASCIIPixel
import org.scalatest.funsuite.AnyFunSuite

class ASCIIPixelTest extends AnyFunSuite {
  test("ASCIIPixel getValue returns correct value") {
    val asciiPixel1 = ASCIIPixel('A')
    val asciiPixel2 = ASCIIPixel('B')
    val asciiPixel3 = ASCIIPixel('C')
    val asciiPixel4 = ASCIIPixel('D')
    val asciiPixel5 = ASCIIPixel('E')
    assert(asciiPixel1.getValue == 'A')
    assert(asciiPixel2.getValue == 'B')
    assert(asciiPixel3.getValue == 'C')
    assert(asciiPixel4.getValue == 'D')
    assert(asciiPixel5.getValue == 'E')
  }

  test("ASCIIPixel should handle non-printable characters") {
    val asciiPixel = ASCIIPixel('\n')
    assert(asciiPixel.getValue == '\n')
  }
}
