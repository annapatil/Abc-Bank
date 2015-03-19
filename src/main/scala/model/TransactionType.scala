package model

/**
 * @author sandeep
 */
object TransactionType extends Enumeration {
    type TransactionType = Value
    val DEPOSIT, WITHDRAWAL = Value
}