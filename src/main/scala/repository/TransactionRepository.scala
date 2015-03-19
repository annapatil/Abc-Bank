package repository
import scala.collection.mutable
import model.Transaction
import model.Customer
import model.AccountType._

object TransactionRepository {

  private val transactions = mutable.Set[Transaction]()
  
  def getTransactionsForCustomer( cust: Customer, accountType: AccountType) = {
    ???
  }
  
  def addTransaction( transaction: Transaction ) {
    ???
  }
}