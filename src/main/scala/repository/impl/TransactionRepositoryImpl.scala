package repository.impl

import scala.collection.mutable
import model.Transaction
import model.Customer
import model.AccountType._
import repository.TransactionRepository

class TransactionRepositoryImpl extends TransactionRepository {

  private val transactions = mutable.Set[Transaction]()

  @Override
  def getTransactionsForCustomer(custId: String, accountType: AccountType): Set[Transaction] = {
    transactions.filter(transaction => {
      (transaction.account.customer.custId == custId) &&
        (transaction.account.accountType == accountType)
    }).toSet
  }

  @Override
  def getAllTransactions: Set[Transaction] = {
    transactions.toSet
  }
  
  @Override
  def addTransaction(transaction: Transaction) {
    transactions += transaction
  }
}