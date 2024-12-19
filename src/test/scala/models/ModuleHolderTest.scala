package models

import converter.{ASCIIConverter, GreyScaleConverter, TestASCIIConverter, TestGreyScaleConverter}
import exporter.{Exporter, TestImageExporter}
import filter.{ImageFilter, TestImageFilter}
import loader.{ImageLoader, TestImageLoader}
import models.Image.{GreyScaleImage, Image, RGBImage}
import models.Pixel.GreyScalePixel
import models.conversionTable.PaulBourkeTable
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class ModuleHolderTest extends AnyFunSuite with MockitoSugar {

  val loader: ImageLoader = new TestImageLoader
  val greyScaleConverter: GreyScaleConverter[RGBImage] = new TestGreyScaleConverter
  val filter: ImageFilter[GreyScaleImage] = new TestImageFilter
  val asciiConverter: ASCIIConverter[GreyScaleImage, GreyScalePixel] = TestASCIIConverter(new PaulBourkeTable)
  val exporter: Exporter[Image] = new TestImageExporter

  val moduleHolder = new ModuleHolder(loader, greyScaleConverter, filter, asciiConverter, exporter)

  test("ModuleHolder returns the correct loader") {
    assert(moduleHolder.getLoader == loader)
  }

  test("ModuleHolder returns the correct GreyScaleConverter") {
    assert(moduleHolder.getGrey == greyScaleConverter)
  }

  test("ModuleHolder returns the correct ImageFilter") {
    assert(moduleHolder.getFilter == filter)
  }

  test("ModuleHolder returns the correct ASCIIConverter") {
    assert(moduleHolder.getASCII == asciiConverter)
  }

  test("ModuleHolder returns the correct Exporter") {
    assert(moduleHolder.getExporter == exporter)
  }
}
