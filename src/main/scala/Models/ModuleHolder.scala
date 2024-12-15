package Models

import Converter.{ASCIIConverter, GreyScaleConverter}
import Exporter.Exporter
import Loader.ImageLoader
import Models.Image.{ASCIIImage, GreyScaleImage, RGBImage}
import Filter.Filter
import Models.Pixel.GreyScalePixel

class ModuleHolder (loader: ImageLoader,
                    greyScaleConverter: GreyScaleConverter[RGBImage],
                    filter: Filter[GreyScaleImage],
                    asciiConverter: ASCIIConverter[GreyScaleImage, GreyScalePixel],
                    exporter: Exporter[ASCIIImage]) {
}
