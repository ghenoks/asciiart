package filter

import models.BusinessError
import models.Image.GreyScaleImage

/*
 * Used to hold multiple GreyScale-Image Filters and run them
 * Returns BusinessError if any filter fails
 */
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
