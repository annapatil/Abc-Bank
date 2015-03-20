package bank

import model.Customer
import model.Account
import model.AccountType._
import reporting.AccountStatement
import reporting.customer.CustomerReport
import reporting.interest.InterestReport

trait Bank {

  def addCustomer( custName: String ) : Customer
  def openAccount( custId: String, accountType: AccountType) : Account

  def deposit(account: Account, amount: Int, memo:String)
  def withdraw(account: Account, amount: Int, memo:String)
  
  def createAccountStatement( custId: String) : AccountStatement
  def createCustomerReport: CustomerReport
  def createInterestReport: InterestReport
}