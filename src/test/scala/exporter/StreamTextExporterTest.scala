package exporter

import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream

class StreamTextExporterTest extends AnyFunSuite {
  test("Write") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)

    exporter.output("Ahoj")

    assert(stream.toString("UTF-8") == "Ahoj")
  }

}
