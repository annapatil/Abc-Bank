package interest

import model.Account

trait InterestCalculator {

  def calculateDailyInterest(account: Account): Int
}