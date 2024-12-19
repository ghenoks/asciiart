package models

import org.scalatest.funsuite.AnyFunSuite

class ArgumentTest extends AnyFunSuite {
  test("Argument is initialized correctly with name and value") {
    val arg = Argument("output", Some("file.txt"))
    assert(arg.name == "output")
    assert(arg.value.contains("file.txt"))
  }

  test("Argument is initialized with None for value") {
    val arg = Argument("output", None)
    assert(arg.name == "output")
    assert(arg.value.isEmpty)
  }

  test("Arguments with the same name and value are equal") {
    val arg1 = Argument("output", Some("file.txt"))
    val arg2 = Argument("output", Some("file.txt"))
    assert(arg1 == arg2)
  }

  test("Arguments with different names are not equal") {
    val arg1 = Argument("output", Some("file.txt"))
    val arg2 = Argument("input", Some("file.txt"))
    assert(arg1 != arg2)
  }

  test("Arguments with different values are not equal") {
    val arg1 = Argument("output", Some("file.txt"))
    val arg2 = Argument("output", Some("output.txt"))
    assert(arg1 != arg2)
  }
}
