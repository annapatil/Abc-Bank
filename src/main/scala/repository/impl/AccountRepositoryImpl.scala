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
    require(customer!=null)
    require(accountType!=null)

    val now = System.nanoTime.toString
    val accountId = s"${customer.custId}_${accountType.toString()}_${now}"
    val account = Account(accountId, accountType, customer, 0)
    accounts(accountId) = account
    account
  }

  @Override
  def getAccount(accountId: String): Account = {
    require(accountId!=null)
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
    require(custId!=null)
    accounts.filter(mapEntry => {
      val (_, account) = mapEntry
      account.customer.custId == custId
    }).map(mapEntry => {
      val (_, accountCust) = mapEntry
      accountCust
    }).toSet
  }

  @Override
  def withdraw(accountId: String, amount: Int) = {
    require(amount>0)
    require(accountId!=null)
    
    if (accounts.isDefinedAt(accountId)) {
      val oldAccount = accounts(accountId)
      if( oldAccount.balance-amount<0) {
        throw new Exception(s"Withdrawal amount $amount is more than ${oldAccount.balance}")
      }
      val newAccount = Account(accountId, oldAccount.accountType, oldAccount.customer, oldAccount.balance-amount)
      accounts(accountId) = newAccount
      newAccount
    } else {
      throw new NoSuchElementException(s"Account with id ${accountId} does not exists")
    }
  }

  @Override
  def deposit(accountId: String, amount: Int) = {
    require(amount>0)
    require(accountId!=null)
    
    if (accounts.isDefinedAt(accountId)) {
      val oldAccount = accounts(accountId)
      val newAccount = Account(accountId, oldAccount.accountType, oldAccount.customer, oldAccount.balance+amount)
      accounts(accountId) = newAccount
      newAccount
    } else {
      throw new NoSuchElementException(s"Account with id ${accountId} does not exists")
    }
  }
}