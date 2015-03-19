package bank

import org.scalatest._
import interest.RangeBasedCalculator

class RangeBasedCalculatorSpec extends FlatSpec {

  /*
  "1" should "2" in {
    //upto $1000, annual rate is 1%
    //from $1001 to $10000 annual rate is 2%
    val rbc = RangeBasedCalculator( Map(100000->1.0,1000000->2.0))
    val initialDeposit = 500000
    //interest on first 100000 is 100000*0.01 = 1000
    //interest for remaining 400000 = 400000*.02 = 8000
    //total interst = 9000 which is $90 for the year
    //so daily is 25 cents
    val checkingAccount = Account(1,AccountType.CHECKING)
    checkingAccount.deposit(initialDeposit,"Initial Deposit")
    assert( rbc.calculateDailyInterest(checkingAccount) == 25 )
  }
  */
}