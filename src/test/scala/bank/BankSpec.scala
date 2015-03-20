package bank

import org.scalatest._
import model.AccountType._

class BankSpec extends FlatSpec {

  "createAccountStatement" should "work as expected 0" in {
    val bank: Bank = new BankImpl("Abc-Bank")
    //add 10 customers, each with three accounts
    val custIds = (1 to 10).map(i=>{
      val cust = bank.addCustomer(s"Cust$i")
      bank.openAccount(cust.custId, CHECKING)
      bank.openAccount(cust.custId, SAVINGS)
      bank.openAccount(cust.custId, MAX_SAVINGS)
      cust.custId
    })    
    
    val cs = bank.createAccountStatement(custIds(0))
    assert( cs.accounts.size == 3)
    val transactionsMap = cs.transactionMap
    transactionsMap.foreach(x=>x._2.size==0)
  }

  "createAccountStatement" should "work as expected 1" in {
    val bank: Bank = new BankImpl("Abc-Bank")
    //add 10 customers, each with three accounts
    val custIds = (1 to 10).map(i=>{
      val cust = bank.addCustomer(s"Cust$i")
      val ca = bank.openAccount(cust.custId, CHECKING)
      bank.deposit(ca, 100, "100")
      val sa = bank.openAccount(cust.custId, SAVINGS)
      bank.deposit(sa, 100, "100")
      val msa = bank.openAccount(cust.custId, MAX_SAVINGS)
      bank.deposit(msa, 100, "100")
      cust.custId
    })    
    
    val cs = bank.createAccountStatement(custIds(0))
    assert( cs.accounts.size == 3)
    val transactionsMap = cs.transactionMap
    transactionsMap.foreach(x=>x._2.size==1)
  }
}