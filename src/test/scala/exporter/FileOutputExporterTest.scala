package exporter

import helpers.TestWithFiles
import org.scalatest.funsuite.AnyFunSuite

import java.io.File

class FileOutputExporterTest extends AnyFunSuite with TestWithFiles {

    test("No file exists") {
      val fileName = getTestFile

      try {
        ensureDeleted(fileName)

        val file = new File(fileName)
        val exporter = new FileOutputExporter(file)

        exporter.output("Ahoj")
        exporter.close()

        assertFileContent(fileName, "Ahoj")
      }
      finally {
        ensureDeleted(fileName)
      }
    }

    test("File already exists") {
      val fileName = getTestFile

      try {
        ensureCreated(fileName)

        val file = new File(fileName)
        val exporter = new FileOutputExporter(file)

        exporter.output("Ahoj")
        exporter.close()

        assertFileContent(fileName, "Ahoj")
      }
      finally {
        ensureDeleted(fileName)
      }
    }
  }
