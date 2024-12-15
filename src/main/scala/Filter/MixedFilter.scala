package Filter

import Models.Image.GreyScaleImage
import Models.Pixel.GreyScalePixel

class MixedFilter (filters: List[ImageFilter[GreyScaleImage]]) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {

    var filteredImage = image

    filters.foreach {
      filter => filteredImage = filter.applyFilter(filteredImage)
    }

    filteredImage
  }
}
