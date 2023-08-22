package spring.learning.springframework.app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.learning.springframework.app.entity.Customer;
import spring.learning.springframework.app.helpers.SortUtils;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    // inject session factory: bean defined in spring-mvc-servlet.xml
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers(int sortField) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // determine sort field
        String fieldName = null;

        switch (sortField) {
            case SortUtils.FIRST_NAME:
                fieldName = "firstName";
                break;
            case SortUtils.LAST_NAME:
                fieldName = "lastName";
                break;
            case SortUtils.EMAIL:
                fieldName = "email";
                break;
            default:
                // if nothing matches the default to sort by lastName
                fieldName = "lastName";
        }

        // create query
        Query<Customer> query =
                currentSession.createQuery(
                        "FROM Customer order by " + fieldName,
                        Customer.class
                );

        // execute query
        // return the results
        return query.getResultList();
    }

    // save or update !
    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        if(customer.getId() != null)
            currentSession.merge(customer);
        else
            currentSession.persist(customer);
    }

    @Override
    public Customer getCustomer(long customerId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Customer.class, customerId);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Session currentSession = sessionFactory.getCurrentSession();
        // Query<Customer> query= currentSession
        //        .createQuery("DELETE FROM Customer c where c.id =:customerId", Customer.class);
        //query.setParameter("customerId", customerId);
        currentSession.remove(currentSession.get(Customer.class, customerId));
    }

    @Override
    public List<Customer> searchCustomers(String searchedName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query query = null;

        // only search by name if theSearchName is not empty
        if (searchedName != null && searchedName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            query =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + searchedName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            query =currentSession.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list

        // return the results
        return query.getResultList();
    }
}
