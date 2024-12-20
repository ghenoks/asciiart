package models.conversionTable

/*
 * Conversion Table used to map values onto symbols from Paul Bourke table
 * Divides the numbers from 0-255 equally into the symbols of Paul Bourke table
 */
case class PaulBourkeTable() extends LinearConversionTable("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ") {
}