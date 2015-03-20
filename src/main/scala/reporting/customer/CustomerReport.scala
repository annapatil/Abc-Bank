package reporting.customer

import model.Customer


case class CustomerDetail( val cust: Customer, val totalAccounts: Int) {

}

case class CustomerReport( val totalCustomers: Int, val totalAccounts: Int, custDetails: Set[CustomerDetail]) {

}