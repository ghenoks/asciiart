package ui.argumentParser

import models.{Argument, BusinessError}
import org.scalatest.funsuite.AnyFunSuite

class MyArgumentParserTest extends AnyFunSuite {
  test("MyArgumentParser should parse valid arguments correctly") {
    val args = List(
      "--image", "test-image.jpg",
      "--rotate", "+90",
      "--invert",
      "--output-file", "../outputs/output.txt",
      "--output-console",
      "--table", "bourke-small"
    )

    val parser = new MyArgumentParser(args)
    val result = parser.parseArgs()

    val expectedArguments = List(
      Argument("image", Some("test-image.jpg")),
      Argument("rotate", Some("+90")),
      Argument("invert", None),
      Argument("output-file", Some("../outputs/output.txt")),
      Argument("output-console", None),
      Argument("table", Some("bourke-small"))
    )

    assert(result == Right(expectedArguments))
  }

  test("MyArgumentParser should return error for unexpected value without command") {
    val args = List(
      "--image", "test-image.jpg",
      "unexpected-value"
    )

    val parser = new MyArgumentParser(args)
    val result = parser.parseArgs()

    assert(result == Left(BusinessError("Invalid input: unexpected value detected without command")))

    val args2 = List(
      "unexpected-value",
      "--image", "test-image.jpg"
    )

    val parser2 = new MyArgumentParser(args)
    val result2 = parser.parseArgs()

    assert(result2 == Left(BusinessError("Invalid input: unexpected value detected without command")))
  }

  test("MyArgumentParser should handle a single command correctly") {
    val args = List(
      "--invert"
    )

    val parser = new MyArgumentParser(args)
    val result = parser.parseArgs()

    val expectedArguments = List(Argument("invert", None))
    assert(result == Right(expectedArguments))
  }

  test("MyArgumentParser should handle no commands at all") {
    val args = List.empty[String]

    val parser = new MyArgumentParser(args)
    val result = parser.parseArgs()

    assert(result == Right(Nil))
  }
}
