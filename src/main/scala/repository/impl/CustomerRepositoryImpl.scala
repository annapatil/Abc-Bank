package repository
import scala.collection.mutable
import model.Customer

class CustomerRepositoryImpl extends CustomerRepository {

  private val customers = mutable.Map[String,Customer]()
  
  @Override
  def getAllCustomers : Set[Customer]= {
    customers.map(x=>x._2).toSet
  }
  
  @Override
  def createCustomer( custName: String) : Customer = {
    val now = System.nanoTime.toString
    val custId = s"${custName}__${now}"
    val customer = Customer(custId, custName)
    customers(custId) = customer
    customer
  }

  def getCustomer(custId: String): Customer = {
    customers(custId)
  }
  
 }