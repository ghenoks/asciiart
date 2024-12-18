package filter

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel

class MixedFilter (filters: List[ImageFilter[GreyScaleImage]]) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {

    var filteredImage = image

    filters.foreach {
      filter => filteredImage = filter.applyFilter(filteredImage)
    }
    filteredImage
  }
}
