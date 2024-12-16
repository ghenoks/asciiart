package Models

import Converter.{ASCIIConverter, GreyScaleConverter}
import Exporter.Exporter
import Loader.ImageLoader
import Models.Image.{GreyScaleImage, Image, RGBImage}
import Filter.Filter
import Models.Pixel.GreyScalePixel

class ModuleHolder (loader: ImageLoader,
                    greyScaleConverter: GreyScaleConverter[RGBImage],
                    filter: Filter[GreyScaleImage],
                    asciiConverter: ASCIIConverter[GreyScaleImage, GreyScalePixel],
                    exporters: List[Exporter[Image]]) {
}
