package models

/*
 * Represents a command 
 * Only serves as a information container
 * Name can be for example "rotate" / "invert"
 * Value can be Some("+90") / None
 */
case class Argument (name: String, value: Option[String]) {
}
