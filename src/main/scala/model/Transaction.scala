package model

import java.util.Date
import TransactionType._

/**
 * if the amount is -ve then its a withdrawal, else its a deposit
 */
case class Transaction( val date: Date, val transactionType: TransactionType, 
    val account: Account, val memo: String, val amount: Int, val newBalance: Int) {
  

}