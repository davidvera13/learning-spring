package spring.learning.springframework.app.dao;

import spring.learning.springframework.app.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers(int sortField);
    void saveCustomer(Customer customer);
    Customer getCustomer(long customerId);
    void deleteCustomer(Long customerId);
    List<Customer> searchCustomers(String searchedName);
}
