package repository

import model.Customer
import model.AccountType._
import model.Transaction

trait TransactionRepository {

  def getTransactionsForCustomer( custId: String, accountType: AccountType) : Set[Transaction]
  def getAllTransactions : Set[Transaction]
  def addTransaction( transaction: Transaction ) : Unit
}