package repository

import org.scalatest._
import org.scalatest.FlatSpec
import model.Account
import model.AccountType._
import model.Customer
import repository.impl.AccountRepositoryImpl
import scala.util.Random

class AccountRepositoryImplSpec extends FlatSpec {

  "Adding accounts" should "increase the size of repository appropriately" in {
    val accountRepo: AccountRepository = new AccountRepositoryImpl
    //Empty repo
    assert(accountRepo.getAllAccounts.size == 0)
    val john = Customer("C1", "John")
    accountRepo.createAccount(john,CHECKING)
    assert(accountRepo.getAllAccounts.size == 1)

    accountRepo.createAccount(john,SAVINGS)
    assert(accountRepo.getAllAccounts.size == 2)
  }
  
  "Duplicate account" should "not be allowed to be added" in  {
    val accountRepo: AccountRepository = new AccountRepositoryImpl
    //Empty repo
    assert(accountRepo.getAllAccounts.size == 0)
    val john = Customer("C1", "John")
    accountRepo.createAccount(john,CHECKING)
    try {
      accountRepo.createAccount(john,SAVINGS)
      assert( false )
    } catch {
      case exp: Exception => 
      
    }
  }
  
  "getAccount" should "work properly under all cirsumstances" in  {
    val accountRepo: AccountRepository = new AccountRepositoryImpl
    assert(accountRepo.getAllAccounts.size == 0)
    val john = Customer("C1", "John")
    val accountIds = (1 to 15).map ( _=> {
      val account = accountRepo.createAccount(john,CHECKING)
      account.accountId
    } )
    accountIds.foreach { accountId => {
      assert( accountRepo.getAccount(accountId)!=null)
    } }
    accountRepo.getAllAccounts.foreach ( account => {
      val balance = account.balance
      assert( balance == 0 )
    } )
  }

  "deposit" should "work properly under all cirsumstances" in  {
    val accountRepo: AccountRepository = new AccountRepositoryImpl
    assert(accountRepo.getAllAccounts.size == 0)
    val john = Customer("C1", "John")
    (1 to 15).map ( _=>accountRepo.createAccount(john,CHECKING) )
    accountRepo.getAllAccounts.foreach ( a => accountRepo.deposit(a.accountId, a.balance+100) )
    accountRepo.getAllAccounts.foreach ( account => {
      val balance = account.balance
      assert( balance ==100 )
    } )
  }

  "withdraw" should "work properly under all cirsumstances" in  {
    val accountRepo: AccountRepository = new AccountRepositoryImpl
    assert(accountRepo.getAllAccounts.size == 0)
    val john = Customer("C1", "John")
    (1 to 15).map ( _=>accountRepo.createAccount(john,CHECKING) )
    accountRepo.getAllAccounts.foreach ( a => accountRepo.deposit(a.accountId, 100) )
    accountRepo.getAllAccounts.foreach ( a => accountRepo.withdraw(a.accountId, 50) )
    accountRepo.getAllAccounts.foreach ( account => {
      val balance = account.balance
      assert( balance ==50 )
    } )
  }
}