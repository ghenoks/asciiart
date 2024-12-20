package models.conversionTable

/*
 * Conversion Table used to map values onto symbols from Paul Bourke table
 * Divides the numbers 0-99 to symbol(0) and from 100-255 equally into the rest of the symbols of Paul Bourke table
 */
case class NonLinearPaulBourkeTable() extends NonLinearConversionTable("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ") {
}
