package spring.learning.springframework.app.services;

import org.springframework.stereotype.Service;
import spring.learning.springframework.app.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers(int sortField);
    void saveCustomer(Customer customer);
    Customer getCustomer(long customerId);
    public void deleteCustomer(Long customerId);
    List<Customer> searchCustomers(String searchedName);
}
