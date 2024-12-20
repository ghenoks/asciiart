package converter

import models.Image.{GreyScaleImage, Image}

/*
 * Converts image to GreyScaleImage
 */
trait GreyScaleConverter[T <: Image] extends ImageConverter[T, GreyScaleImage] {

}
