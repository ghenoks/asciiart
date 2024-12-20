package filter

import models.BusinessError
import models.Image.GreyScaleImage

/*
 * GreyScale-Image filter used in testing
 * Always returns BusinessError
 */
class FailImageFilter extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {
    Left(BusinessError("Filter failed."))
  }
}
