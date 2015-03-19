package reporting.customer


case class CustomerDetail( val name: String, val totalAccounts: Int) {

}
case class CustomerReport( val totalCustomers: Int, val totalAccounts: Int, custDetails: Set[CustomerDetail]) {

}