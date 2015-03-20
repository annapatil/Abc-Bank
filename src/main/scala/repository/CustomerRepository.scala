package repository

import model.Customer

trait CustomerRepository {

  def getAllCustomers : Set[Customer]
  def createCustomer( custName: String) : Customer
  def getCustomer( custId: String) : Customer
}