package filter

import models.BusinessError
import models.Image.GreyScaleImage

class GreyImageIdentityFilter extends IdentityFilter[GreyScaleImage] with ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = Right(image)
}
