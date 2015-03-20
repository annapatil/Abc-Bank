package bank
import model.TransactionType._
import model.Account
import repository.AccountRepository
import java.util.Calendar
import model.Transaction
import repository.TransactionRepository

class TransactionManager(val accountRepo: AccountRepository, val transactionRepo: TransactionRepository) {

  def deposit(accountId: String, amount: Int, memo: String) {
    require(amount > 0)
    require(memo != null)
    require(accountId != null)

    val account = accountRepo.deposit(accountId, amount)
    val now = Calendar.getInstance.getTime
    val transaction = Transaction(now, DEPOSIT, account, memo, amount, account.balance)
    transactionRepo.addTransaction(transaction);
  }

  def withdraw(accountId: String, amount: Int, memo: String) {
    require(accountId != null)
    require(memo != null)
    require(amount > 0)

    val account = accountRepo.withdraw(accountId, amount)
    val now = Calendar.getInstance.getTime
    val transaction = Transaction(now, DEPOSIT, account, memo, amount, account.balance)
    transactionRepo.addTransaction(transaction);
  }

  def transfer(fromAccountId: String, toAccountId: String, amount: Int, memo: String) {
    withdraw(fromAccountId,amount,memo)
    deposit(toAccountId,amount,memo)
  }

}