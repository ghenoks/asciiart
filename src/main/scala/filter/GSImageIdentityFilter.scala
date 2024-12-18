package filter

import models.Image.GreyScaleImage

class GSImageIdentityFilter extends IdentityFilter[GreyScaleImage] with ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = image
}
