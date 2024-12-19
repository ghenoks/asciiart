package converter

import models.Image.{GreyScaleImage, Image}

trait GreyScaleConverter[T <: Image] extends ImageConverter[T, GreyScaleImage] {

}
