package repository

import model.Customer

trait CustomerRepository {

  def getAllCustomers : Set[Customer]
  def addCustomer( newCust: Customer)
  def getCustomer( custId: String) : Customer
}