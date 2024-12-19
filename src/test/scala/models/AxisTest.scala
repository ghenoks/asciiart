package models

import org.scalatest.funsuite.AnyFunSuite

class AxisTest extends AnyFunSuite {
  test("Axis enumeration contains the expected values") {
    assert(Axis.values.map(_.toString).contains("X"))
    assert(Axis.values.map(_.toString).contains("Y"))
  }

  test("Axis enumeration contains exactly two values") {
    assert(Axis.values.size == 2)
  }

  test("Axis enumeration does not contain unexpected values") {
    val unexpectedValue = "Z"
    val allValues = Axis.values.map(_.toString)

    assert(!allValues.contains(unexpectedValue))
  }
}
