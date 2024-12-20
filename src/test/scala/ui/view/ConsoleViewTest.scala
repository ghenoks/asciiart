package ui.view

import org.scalatest.funsuite.AnyFunSuite

import java.io.{ByteArrayOutputStream, PrintStream}

class ConsoleViewTest extends AnyFunSuite {
  test("showErrorMessage should print the correct error message") {
    val consoleView = new ConsoleView
    val outputStream = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(outputStream)) {
      consoleView.showErrorMessage("Test error message")
    }
    val output = outputStream.toString.trim
    assert(output == "Error detected: Test error message")
  }

  test("showSuccessMessage should print the success message") {
    val consoleView = new ConsoleView
    val outputStream = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(outputStream)) {
      consoleView.showSuccessMessage()
    }
    val output = outputStream.toString.trim
    assert(output == "Conversion was SUCCESSFUL :)")
  }
}
