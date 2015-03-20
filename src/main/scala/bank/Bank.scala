package bank

import model.Customer
import model.Account
import model.AccountType._
import reporting.AccountStatement
import reporting.customer.CustomerReport
import reporting.interest.InterestReport

trait Bank {

  def addCustomer( cust: Customer )
  def openAccount( custId: String, accountType: AccountType) : Account
  def createCustomerStatement( custId: String) : AccountStatement
  def createCustomerReport: CustomerReport
  def createInterestReport: InterestReport
}