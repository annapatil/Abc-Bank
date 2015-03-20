package repository.impl

import model.Customer
import model.Account
import model.AccountType._
import scala.collection.mutable
import repository.AccountRepository

class AccountRepositoryImpl extends AccountRepository {

  private val accounts = mutable.Map[String, Account]()

  @Override
  def createAccount(customer: Customer, accountType: AccountType): Account = {
    val now = System.nanoTime.toString
    val accountId = s"${customer.custId}_${accountType.toString()}_${now}"
    val account = Account(accountId, accountType, customer, 0)
    accounts(accountId) = account
    account
  }

  @Override
  def getAccount(accountId: String): Account = {
    accounts(accountId)
  }

  @Override
  def getAllAccounts: Set[Account] = {
    accounts.map(mapEntry => {
      val (_, accountCust) = mapEntry
      accountCust
    }).toSet
  }

  @Override
  def getAllCustomerAccounts(custId: String): Set[Account] = {
    accounts.filter(mapEntry => {
      val (_, account) = mapEntry
      account.customer.custId == custId
    }).map(mapEntry => {
      val (_, accountCust) = mapEntry
      accountCust
    }).toSet
  }

  @Override
  def updateAccountBalance(accountId: String, newBalance: Int) = {
    if (accounts.isDefinedAt(accountId)) {
      val oldAccount = accounts(accountId)
      val newAccount = Account(accountId, oldAccount.accountType, oldAccount.customer, newBalance)
      accounts(accountId) = newAccount
      newAccount
    } else {
      throw new NoSuchElementException(s"Account with id ${accountId} does not exists")
    }
  }
}