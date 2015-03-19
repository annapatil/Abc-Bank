package interest

import model.Account

case class RangeBasedCalculator(val rangeMap: Map[Int,Double]) extends InterestCalculator {
  
  //we get the balances sorted first
  val sortedMap = rangeMap.toSeq.sortBy(_._1)
  
  def calculateDailyInterest(account: Account): Int = {
    var dailyInterest = 0.0
    var previousBalance = 0
    var accountBalance = account.balance
    sortedMap.foreach( value => {
      val( balance, annualRate) = value
      var amount = balance - previousBalance
      if ( amount > account.balance) {
        amount = account.balance
      }
      dailyInterest += InterestRateUtils.calculateDailyRate(amount, annualRate)
      previousBalance = balance
    })
    dailyInterest.intValue()
  }
  
}