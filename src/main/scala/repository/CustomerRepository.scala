package repository
import scala.collection.mutable
import model.Customer

object CustomerRepository {

  private val customers = mutable.Set[Customer]()
  
  def getAllCustomers = {
    customers.toSet
  }
  
  def addCustomer( newCust: Customer) {
    customers += newCust
  }
  
  /*
   * we can add addition methods to get a subset of all customers
   */
}