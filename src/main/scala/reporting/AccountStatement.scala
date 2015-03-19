package reporting

import model.Transaction
import model.AccountType._

case class AccountBalance( val accountId: Int, val accoutType:AccountType, val accountBalance: Int) {

}
case class AccountStatement( val accountBalance: AccountBalance, val transactions: Set[Transaction]) {

}