package spring.learning.springframework.app.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.learning.springframework.app.dao.CustomerDao;
import spring.learning.springframework.app.entity.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    @Override
    @Transactional          // no need to begin transaction and commit transaction
    public List<Customer> getCustomers(int sortField) {
        return customerDao.getCustomers(sortField);
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(long customerId) {
        return customerDao.getCustomer(customerId);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {
        customerDao.deleteCustomer(customerId);

    }

    @Override
    @Transactional
    public List<Customer> searchCustomers(String searchedName) {
        return customerDao.searchCustomers(searchedName);
    }
}
