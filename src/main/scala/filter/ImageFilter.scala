package filter

import models.Image.Image

/*
 * Used to Apply filters to Images
 */
trait ImageFilter[S <: Image] extends Filter[S] {

}
