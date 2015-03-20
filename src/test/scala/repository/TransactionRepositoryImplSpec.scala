package repository

import org.scalatest._
import repository.impl.TransactionRepositoryImpl
import model.Transaction
import java.util.Calendar
import model.TransactionType
import model.Customer
import model.Account
import model.AccountType._


class TransactionRepositoryImplSpec extends FlatSpec {

  "Transaction count" should "be consistent with new transaction addition" in  {
    val transactionRepository: TransactionRepository = new TransactionRepositoryImpl
    val john = Customer("C1", "John")
    val account1 = Account("A1", CHECKING, john, 10000)

    val t1 = Transaction(Calendar.getInstance.getTime,TransactionType.DEPOSIT,account1,"Deposit",100,1000)
    val t2 = Transaction(Calendar.getInstance.getTime,TransactionType.DEPOSIT,account1,"Deposit",200,3000)
    
    transactionRepository.addTransaction(t1)
    transactionRepository.addTransaction(t2)
    
    assert( transactionRepository.getAllTransactions.size == 2)
  }
}