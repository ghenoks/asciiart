package models

import converter.{ASCIIConverter, GreyScaleConverter}
import exporter.Exporter
import loader.ImageLoader
import models.Image.{GreyScaleImage, Image, RGBImage}
import filter.ImageFilter
import models.Pixel.GreyScalePixel

class ModuleHolder (loader: ImageLoader,
                    greyScaleConverter: GreyScaleConverter[RGBImage],
                    filter: ImageFilter[GreyScaleImage],
                    asciiConverter: ASCIIConverter[GreyScaleImage, GreyScalePixel],
                    exporters: List[Exporter[Image]]) {
  def getLoader: ImageLoader = loader
  def getGrey: GreyScaleConverter[RGBImage] = greyScaleConverter
  def getFilter: ImageFilter[GreyScaleImage] = filter
  def getASCII: ASCIIConverter[GreyScaleImage, GreyScalePixel] = asciiConverter
  def getExporters: List[Exporter[Image]] = exporters
}
