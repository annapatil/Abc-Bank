package model

import java.util.Calendar
import scala.collection.mutable
import AccountType._

/**
 * Account is always associated with a customer
 */
case class Account(val accountId: Int, val accountType: AccountType, val customer: Customer, val balance: Int )  {
  
}