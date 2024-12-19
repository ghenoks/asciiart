package filter

import models.Image.GreyScaleImage
import models.BusinessError

class TestImageFilter extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {
    Right(image)
  }
}
