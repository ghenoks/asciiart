package Converter

import Models.Image.{GreyScaleImage, Image}

trait GreyScaleConverter[T <: Image] extends Converter[T, GreyScaleImage] {

}
