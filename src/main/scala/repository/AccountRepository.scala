package repository
import scala.collection.mutable
import model.Account
import model.AccountType._
import model.Customer

trait AccountRepository {

  def createAccount( customer: Customer, accountType: AccountType) : Account
  def getAccount( accountId: String) : Account
  def getAllCustomerAccounts( custId: String ) : Set[Account]
  def getAllAccounts: Set[Account]
  def withdraw(accountId: String, amount: Int) : Account
  def deposit(accountId: String, amount: Int) : Account
}