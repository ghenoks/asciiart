package filter

import models.Image.GreyScaleImage

class ImageIdentityFilter extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = image
}
