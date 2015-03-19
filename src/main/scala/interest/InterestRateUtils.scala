package interest

object InterestRateUtils {

  def calculateDailyRate(balance: Int, annualRate: Double) = {
    val dailyInterestRate = annualRate / 365
    val interest = balance * (dailyInterestRate / 100.0)
    interest.intValue()
  }
}