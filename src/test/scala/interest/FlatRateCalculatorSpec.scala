package bank

import model.AccountType
import org.scalatest._
import interest.FlatRateCalculator
import model.Account
import model.Customer

class FlatRateCalculatorSpec extends FlatSpec {

  "Daily interest" should "be 27 cents if deposit is $100 and interest rate is 100%" in {
    val interestCalculator = FlatRateCalculator(100.0)
    val checkingAccount = Account("A1",AccountType.CHECKING,Customer("C1","John"),10000)
    val interest = interestCalculator.calculateDailyInterest(checkingAccount)
    assert( interest==27 )
    
  }
}