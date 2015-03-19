package repository
import scala.collection.mutable
import model.Account
import model.Customer

object AccountRepository {

  private val accounts = mutable.Map[Int,Account]()

  def getAllCustomerAccounts( cust: Customer ) : Set[Account] = {
    ???
  }
  
  def getAllAccounts: Set[Account] = {
    ???
  }
  
  def updateAccount(account: Account) {
    if( accounts.isDefinedAt(account.accountId) ) {
      accounts(account.accountId)=account
    } else {
      throw new NoSuchElementException(s"Account with id ${account.accountId} does not exists")
    }
  }
}