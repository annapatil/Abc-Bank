package model

import java.util.Date

import scala.collection.mutable

/**
 * For simplicity only name attribute is included
 * We can have additional information like age, address, email, etc
 * Customer could be a individual or a business as well
 */
case class Customer( val custId: String, val name: String) {
}