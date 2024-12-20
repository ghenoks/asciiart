package filter

import models.BusinessError
import models.Image.GreyScaleImage

class TestImageFilter2 extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {
    Left(BusinessError("Filter failed."))
  }
}
