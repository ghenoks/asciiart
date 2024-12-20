package exporter

import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream

class StreamTextExporterTest extends AnyFunSuite {
  test("Write into Stream") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)

    exporter.output("Ahoj")

    assert(stream.toString("UTF-8") == "Ahoj")
  }

  test("Write after close throws exception") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)

    exporter.close()
    val exception = intercept[Exception] {
      exporter.output("Ahoj")
    }
    assert(exception.getMessage.contains("The stream is already closed"))
  }

  test("Multiple writes accumulate data") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)

    exporter.output("Hello")
    exporter.output(" World")
    exporter.close()

    assert(stream.toString("UTF-8") == "Hello World")
  }
}
