package interest

import model.Account


/**
 * interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
 */
case class WithdrawalBasedCalculator() extends InterestCalculator {
  
  def calculateDailyInterest(account: Account): Int = {
    ???
  }
}