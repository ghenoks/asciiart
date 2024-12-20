package filter

import models.BusinessError
import models.Image.GreyScaleImage

/* 
 * Returns the same image it gets
 */
class GreyImageIdentityFilter extends IdentityFilter[GreyScaleImage] with ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = Right(image)
}
