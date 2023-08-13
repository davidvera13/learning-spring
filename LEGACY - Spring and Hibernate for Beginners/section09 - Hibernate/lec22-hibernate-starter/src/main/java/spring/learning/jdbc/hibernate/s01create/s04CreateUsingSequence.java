package spring.learning.jdbc.hibernate.s01create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Customer;

// this will create table containing
public class s04CreateUsingSequence {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();

        // try to persist
        try (factory) {
            // create session
            Session session = factory.getCurrentSession();
            System.out.println(">> Beginning transaction");
            session.beginTransaction();
            // save student: save is deprecated since hibernate 6
            // session.save(student)
            System.out.println(">> Creating & Persisting Customer");
            session.persist(new Customer("John", "Lennon", "john@beatles.com"));
            session.persist(new Customer("Ringo", "Star", "ringo@beatles.com"));
            session.persist(new Customer("George", "Harrison", "george@beatles.com"));
            session.persist(new Customer("Paul", "McCartney", "paul@beatles.com"));
            // commit transaction
            System.out.println(">> Persisting Customer");
            session.getTransaction().commit();
            session.close();
        }
        // no need to close factory, try with resource close it automatically

    }

}
