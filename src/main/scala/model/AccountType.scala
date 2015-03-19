package model

object AccountType extends Enumeration {
  type AccountType = Value
  val SAVINGS, CHECKING, MAX_SAVINGS = Value
}