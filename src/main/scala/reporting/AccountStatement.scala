package reporting

import model.Transaction
import model.AccountType._
import model.Account


case class AccountStatement( val accounts: Set[Account], val transactionMap: Map[AccountType,Set[Transaction]]) {

}