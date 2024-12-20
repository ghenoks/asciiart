package filter

import models.BusinessError
import models.Image.GreyScaleImage

class MixedImageFilter(filters: List[ImageFilter[GreyScaleImage]]) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {

    var filteredImage: Either[BusinessError, GreyScaleImage] = Right(image)

    filters.foreach {
      filter =>
        filteredImage = filteredImage match {
          case Right(img) => filter.applyFilter(img)
          case Left(error) => Left(error)
        }
    }
    filteredImage
  }
}
