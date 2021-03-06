package bank

import model.Account
import model.AccountType._
import model.Customer
import reporting.AccountStatement
import reporting.customer.CustomerReport
import reporting.interest.InterestReport
import reporting.customer.CustomerDetail

class BankImpl(val bankName: String) extends BankBase with Bank {

  def addCustomer(custName: String): Customer = {
    customerRepo.createCustomer(custName)
  }

  def openAccount(custId: String, accountType: AccountType): Account = {
    val customer = customerRepo.getCustomer(custId)
    if (customer == null) {
      throw new Exception(s"No customer with id $custId")
    }
    accountRepo.createAccount(customer, accountType)
  }

  def deposit(account: Account, amount: Int, memo: String): Unit = {
    transactionManager.deposit(account.accountId, amount, memo)
  }

  def withdraw(account: Account, amount: Int, memo: String): Unit = {
    transactionManager.withdraw(account.accountId, amount, memo)
  }

  def createAccountStatement(custId: String): AccountStatement = {
    val customer = customerRepo.getCustomer(custId)
    if (customer == null) {
      throw new Exception(s"No customer with id $custId")
    }
    val custAccounts = accountRepo.getAllCustomerAccounts(custId)
    val transactionMap = custAccounts.map(account => {
      val transactions = transactionRepo.getTransactionsForCustomer(custId, account.accountType)
      account.accountType -> transactions
    }).toMap
    AccountStatement(custAccounts, transactionMap)
  }

  def createCustomerReport: CustomerReport = {
    val allAccounts = accountRepo.getAllAccounts
    val gyc = allAccounts.groupBy { account => account.customer }
    val custDetails = gyc.map( x=> CustomerDetail(x._1,x._2.size) ).toSet
    val totalCustomers = gyc.size
    CustomerReport(totalCustomers, allAccounts.size, custDetails)
  }

  def createInterestReport: InterestReport = {
    InterestReport("Not Implemented Yet!!!")
  }

}
