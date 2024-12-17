package converter

import models.Image.{GreyScaleImage, Image}

trait GreyScaleConverter[T <: Image] extends Converter[T, GreyScaleImage] {

}
