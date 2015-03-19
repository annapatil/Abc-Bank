package bank
import model.TransactionType._
import model.Account
import repository.AccountRepository
import java.util.Calendar
import model.Transaction
import repository.TransactionRepository

object TransactionManager {

  def deposit(account: Account, amount: Int, memo: String) {
    require( amount > 0)
    require( memo != null )
    require( account != null )
    
    val now = Calendar.getInstance.getTime
    val newBalance = account.balance + amount
    val newAccount = Account(account.accountId,account.accountType,account.customer,newBalance)
    AccountRepository.updateAccount(newAccount)
    val transaction = Transaction(now,DEPOSIT,account,memo,amount,newBalance)
    TransactionRepository.addTransaction(transaction);
  }

  def withDraw( account: Account, amount: Int, memo: String) {
    require( account != null )
    require( amount <= account.balance)
    require( memo != null )
    
    val now = Calendar.getInstance.getTime
    val newBalance = account.balance + amount
    val newAccount = Account(account.accountId,account.accountType,account.customer,newBalance)
    AccountRepository.updateAccount(newAccount)
    val transaction = Transaction(now,DEPOSIT,account,memo,amount,newBalance)
    TransactionRepository.addTransaction(transaction);
  }
}