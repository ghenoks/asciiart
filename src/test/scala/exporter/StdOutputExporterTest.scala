package exporter

import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream

class StdOutputExporterTest extends AnyFunSuite {
  test("StdOutputExporter outputs to standard output") {

    val outputStream = new ByteArrayOutputStream()
    val originalOut = System.out

    try {
      System.setOut(new java.io.PrintStream(outputStream))

      val exporter = new StdOutputExporter()
      val result = exporter.output("Hello, world!")
      exporter.close()

      assert(result.isRight)
      assert(outputStream.toString("UTF-8") == "Hello, world!")
    } finally {
      System.setOut(originalOut)
    }
  }
}
