package bank

import model.AccountType._
import interest.FlatRateCalculator
import interest.InterestCalculator
import interest.RangeBasedCalculator
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import model.Customer
import model.Account
import repository.CustomerRepository
import repository.AccountRepository
import model.TransactionType._

case class BankImpl(val name: String)  {

  /**
   * Each account type is associated with a particular interest calculator.
   * This can always be changed to a different calculator if needed
   */
  val savingsAccountInterestMap = Map(1000->0.1,Integer.MAX_VALUE->0.2)
  
  val maxSavingsAccountInterestMap = Map (1000->2.0,2000->5.0,Integer.MAX_VALUE->10.0)

  private val interestCalculatorMap = mutable.Map[AccountType,InterestCalculator](
      CHECKING->new FlatRateCalculator(0.1),
      SAVINGS->new RangeBasedCalculator(savingsAccountInterestMap),
      MAX_SAVINGS->new RangeBasedCalculator(maxSavingsAccountInterestMap)
  )

  def changeInterestCalculator( accountType: AccountType, newCalculator: InterestCalculator) {
    interestCalculatorMap(accountType) = newCalculator
  }

  /**
   * Calculate daily interest for each account and post the interest to them.
   */
  def processEndOfDay = {
    val allAccounts = AccountRepository.getAllAccounts;   
    allAccounts.foreach ( account => {
      val accountType = account.accountType
      val interestRateCalculator = interestCalculatorMap(accountType)
      val dailyInterest = interestRateCalculator.calculateDailyInterest(account);
      TransactionManager.deposit(account, dailyInterest, "DAILY INTEREST")
    } )
  }
}