package repository
import scala.collection.mutable
import model.Account
import model.Customer

trait AccountRepository {

  def addAccount( account: Account)
  def getAccount( accountId: String) : Account
  def getAllCustomerAccounts( custId: String ) : Set[Account]
  def getAllAccounts: Set[Account]
  def updateAccountBalance(accountId: String, newBalance: Int) : Account
}