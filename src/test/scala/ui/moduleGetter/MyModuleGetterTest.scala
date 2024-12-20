package ui.moduleGetter

import converter.{GreyScaleToASCIIConverter, RGBtoGreyScaleConverter}
import exporter.{FileOutputExporter, ImageExporter, MixedImageExporter, StdOutputExporter}
import filter.{GreyImageIdentityFilter, InversionFilter, MixedImageFilter, RotationFilter}
import loader.{JPGFileImageLoader, JPGFileImageLoaderTest, StdFileImageLoader}
import models.conversionTable.PaulBourkeTable
import models.{Argument, BusinessError, ModuleHolder}
import org.scalatest.funsuite.AnyFunSuite

import java.io.File

class MyModuleGetterTest extends AnyFunSuite {
  test("MyModuleGetter should correctly parse and return a ModuleHolder for valid arguments") {
    val args = List(
      Argument("image", Some("src/main/scala/resources/beetroot.jpg")),
      Argument("rotate", Some("90")),
      Argument("invert", None),
      Argument("output-file", Some("src/main/scala/resources/result2.txt")),
      Argument("output-console", None),
      Argument("table", Some("paulbourke"))
    )

    val moduleGetter = new MyModuleGetter(args)
    val result = moduleGetter.getModules

    result match {
      case Right(modules) =>
        assert(modules.getLoader.isInstanceOf[JPGFileImageLoader])
        assert(modules.getGrey.isInstanceOf[RGBtoGreyScaleConverter])
        assert(modules.getFilter.isInstanceOf[MixedImageFilter])
        assert(modules.getASCII.isInstanceOf[GreyScaleToASCIIConverter])
        assert(modules.getExporter.isInstanceOf[MixedImageExporter])
      case Left(error) =>
        fail(s"Test failed with BusinessError: $error")
    }
  }

  test("MyModuleGetter should return an error if no image command specified") {
    val args = List(
      Argument("rotate", Some("90")),
      Argument("invert", None),
      Argument("output-file", Some("../outputs/output.txt")),
      Argument("output-console", None),
      Argument("table", Some("paulbourke"))
    )

    val moduleGetter = new MyModuleGetter(args)
    val result = moduleGetter.getModules

    result match {
      case Right(modules) => fail(s"Unexpected error: No image was specified")
      case Left(error) => assert(error == BusinessError("No image command specified."))
    }
  }

  test("MyModuleGetter should return an error for invalid image format") {
    val args = List(
      Argument("image", Some("image.gif")),
      Argument("rotate", Some("90")),
      Argument("invert", None),
      Argument("output-file", Some("../outputs/output.txt")),
      Argument("output-console", None),
      Argument("table", Some("paulbourke"))
    )

    val moduleGetter = new MyModuleGetter(args)
    val result = moduleGetter.getModules

    result match {
      case Right(modules) => fail(s"Unexpected error: image format is invalid")
      case Left(error) => assert(error == BusinessError("Unsupported image format. Please use JPG or PNG file."))
    }
  }

  test("MyModuleGetter should return an error if multiple image commands are specified") {
    val args = List(
      Argument("image", Some("image.jpg")),
      Argument("image-random", None),
      Argument("rotate", Some("90")),
      Argument("invert", None),
      Argument("output-file", Some("../outputs/output.txt")),
      Argument("output-console", None),
      Argument("table", Some("paulbourke"))
    )

    val moduleGetter = new MyModuleGetter(args)
    val result = moduleGetter.getModules

    result match {
      case Right(modules) => fail(s"Unexpected error: multiple image commands specified")
      case Left(error) => assert(error == BusinessError("Multiple image commands specified. Please specify only one image command."))
    }
  }

  test("MyModuleGetter should not return error if no filter commands are specified") {
    val args = List(
      Argument("image", Some("src/main/scala/resources/beetroot.jpg")),
      Argument("output-file", Some("src/main/scala/resources/result.txt")),
      Argument("output-console", None),
      Argument("table", Some("paulbourke"))
    )

    val moduleGetter = new MyModuleGetter(args)
    val result = moduleGetter.getModules

    result match {
      case Right(modules) =>
        assert(modules.getLoader.isInstanceOf[JPGFileImageLoader])
        assert(modules.getGrey.isInstanceOf[RGBtoGreyScaleConverter])
        assert(modules.getFilter.isInstanceOf[GreyImageIdentityFilter])
        assert(modules.getASCII.isInstanceOf[GreyScaleToASCIIConverter])
        assert(modules.getExporter.isInstanceOf[MixedImageExporter])
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("MyModuleGetter should return an error if filter arguments are invalid") {
    val args = List(
      Argument("image", Some("image.jpg")),
      Argument("rotate", Some("invalid-value")),
      Argument("invert", None),
      Argument("output-file", Some("../outputs/output.txt")),
      Argument("output-console", None),
      Argument("table", Some("paulbourke"))
    )

    val moduleGetter = new MyModuleGetter(args)
    val result = moduleGetter.getModules

    result match {
      case Right(modules) => fail(s"Unexpected error: multiple image commands specified")
      case Left(error) => assert(error == BusinessError("Invalid argument for Rotation filter."))
    }
  }

  test("MyModuleGetter should return an error if no output command is specified") {
    val args = List(
      Argument("image", Some("image.jpg")),
      Argument("rotate", Some("90")),
      Argument("invert", None),
      Argument("table", Some("paulbourke"))
    )

    val moduleGetter = new MyModuleGetter(args)
    val result = moduleGetter.getModules

    result match {
      case Right(modules) => fail(s"Unexpected error: multiple image commands specified")
      case Left(error) => assert(error == BusinessError("No output command specified."))
    }
  }

  test("MyModuleGetter should return an error if multiple table commands are specified") {
    val args = List(
      Argument("image", Some("image.jpg")),
      Argument("rotate", Some("90")),
      Argument("invert", None),
      Argument("output-file", Some("../outputs/output.txt")),
      Argument("output-console", None),
      Argument("table", Some("paulbourke")),
      Argument("table", Some("nonlinear-paulbourke"))
    )

    val moduleGetter = new MyModuleGetter(args)
    val result = moduleGetter.getModules

    result match {
      case Right(modules) => fail(s"Unexpected error: multiple image commands specified")
      case Left(error) => assert(error == BusinessError("Multiple table commands specified. Please specify only one table command."))
    }
  }

  test("MyModuleGetter should handle an empty argument list gracefully") {
    val args = List.empty[Argument]

    val moduleGetter = new MyModuleGetter(args)
    val result = moduleGetter.getModules

    result match {
      case Right(modules) => fail(s"Unexpected error: multiple image commands specified")
      case Left(error) => assert(error == BusinessError("No image command specified."))
    }
  }
}
