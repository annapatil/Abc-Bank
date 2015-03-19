package reporting

import java.util.Date
import model.Customer
import reporting.customer.CustomerReport
import reporting.interest.InterestReport

object ReportStatementGenerator {

  def createAccountStatement(cust: Customer, startDate: Date, endDate: Date) : Set[AccountStatement] = {
    Set()
  }
  
  def createInterestReport : InterestReport = {
    ???
  }
  
  def createCustomerReport : CustomerReport = {
    ???
  }
}