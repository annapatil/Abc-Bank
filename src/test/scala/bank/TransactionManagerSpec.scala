package bank

import org.scalatest._
import model.Customer
import model.Account
import model.AccountType._
import repository.impl.AccountRepositoryImpl
import repository.AccountRepository
import repository.impl.TransactionRepositoryImpl
import repository.TransactionRepository

class TransactionManagerSpec extends FlatSpec {

  "deposits" should "get reflected in account repository" in {
    val accountRepo: AccountRepository = new AccountRepositoryImpl
    val transactionRepo: TransactionRepository = new TransactionRepositoryImpl
    val transactionManager = new TransactionManager(accountRepo, transactionRepo)
    val john = Customer("C1", "John")
    val account1 = accountRepo.createAccount(john, CHECKING)
    transactionManager.deposit(account1.accountId, 100, "first deposit")
    val newAccount1 = accountRepo.getAccount(account1.accountId)
    assert(newAccount1.balance == 100)

    transactionManager.deposit(account1.accountId, 200, "second deposit")
    transactionManager.deposit(account1.accountId, 300, "third deposit")
    val newAccount2 = accountRepo.getAccount(account1.accountId)
    assert(newAccount2.balance == 600)

  }

  "withdrawals" should "get reflected in account repository" in {
    val accountRepo: AccountRepository = new AccountRepositoryImpl
    val transactionRepo: TransactionRepository = new TransactionRepositoryImpl
    val transactionManager = new TransactionManager(accountRepo, transactionRepo)
    val john = Customer("C1", "John")
    val account1 = accountRepo.createAccount(john, CHECKING)
    transactionManager.deposit(account1.accountId, 800, "+800")
    transactionManager.withdraw(account1.accountId, 100, "first withdrawal")
    val newAccount1 = accountRepo.getAccount(account1.accountId)
    assert(newAccount1.balance == 700)

    transactionManager.withdraw(account1.accountId, 200, "second withdrawal")
    transactionManager.withdraw(account1.accountId, 300, "third withdrawal")
    val newAccount2 = accountRepo.getAccount(account1.accountId)
    assert(newAccount2.balance == 200)

  }

  "withdrawals and deposits" should "get reflected in account repository" in {
    val accountRepo: AccountRepository = new AccountRepositoryImpl
    val transactionRepo: TransactionRepository = new TransactionRepositoryImpl
    val transactionManager = new TransactionManager(accountRepo, transactionRepo)
    val john = Customer("C1", "John")
    val account1 = accountRepo.createAccount(john, CHECKING)
    transactionManager.deposit(account1.accountId, 1000, "+1000")
    transactionManager.withdraw(account1.accountId, 100, "-100")
    transactionManager.deposit(account1.accountId, 500, "+500")
    transactionManager.withdraw(account1.accountId, 200, "-200")
    transactionManager.withdraw(account1.accountId, 300, "-300")
    transactionManager.deposit(account1.accountId, 450, "+450")
    val newAccount2 = accountRepo.getAccount(account1.accountId)
    assert(newAccount2.balance == 1350)

  }
}