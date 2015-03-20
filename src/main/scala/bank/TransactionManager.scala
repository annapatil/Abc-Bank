package bank
import model.TransactionType._
import model.Account
import repository.AccountRepository
import java.util.Calendar
import model.Transaction
import repository.TransactionRepository

class TransactionManager( val accountRepo: AccountRepository, val transactionRepo: TransactionRepository ) {

  def deposit(accountId: String, amount: Int, memo: String) {
    require( amount > 0)
    require( memo != null )
    require( accountId != null )
    
    val now = Calendar.getInstance.getTime
    val account = accountRepo.getAccount(accountId)
    val newBalance = account.balance + amount
    accountRepo.updateAccountBalance(account.accountId,newBalance)
    val transaction = Transaction(now,DEPOSIT,account,memo,amount,newBalance)
    transactionRepo.addTransaction(transaction);
  }

  def withdraw( accountId: String, amount: Int, memo: String) {
    require( accountId != null )
    require( memo != null )
    
    val now = Calendar.getInstance.getTime
    val account = accountRepo.getAccount(accountId)
    val newBalance = account.balance - amount
    if( newBalance < 0 ) {
      throw new Exception(s"Withdrawal amount $amount is more than ${account.balance}")
    }
    val newAccount = accountRepo.updateAccountBalance(account.accountId,newBalance)
    val transaction = Transaction(now,DEPOSIT,account,memo,amount,newBalance)
    transactionRepo.addTransaction(transaction);
  }
}