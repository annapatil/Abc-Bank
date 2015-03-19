package interest

import model.Account

case class FlatRateCalculator( val annualRate: Double)  extends InterestCalculator {
  
  def calculateDailyInterest(account: Account): Int = {
    val balance = account.balance
    InterestRateUtils.calculateDailyRate(balance, annualRate)
  }
}