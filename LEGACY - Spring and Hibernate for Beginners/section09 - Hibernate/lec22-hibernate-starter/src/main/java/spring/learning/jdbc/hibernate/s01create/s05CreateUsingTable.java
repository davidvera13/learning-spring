package spring.learning.jdbc.hibernate.s01create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Client;

// this will create table containing
public class s05CreateUsingTable {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();

        // try to persist
        try (factory) {
            // create session
            Session session = factory.getCurrentSession();
            // create student
            System.out.println(">> Beginning transaction");
            session.beginTransaction();
            // save student: save is deprecated since hibernate 6
            // session.save(student)
            System.out.println(">> Creating & Persisting Client");
            session.persist(new Client("John", "Lennon", "john@beatles.com"));
            session.persist(new Client("Ringo", "Star", "ringo@beatles.com"));
            session.persist(new Client("George", "Harrison", "george@beatles.com"));
            session.persist(new Client("Paul", "McCartney", "paul@beatles.com"));
            // commit transaction
            System.out.println(">> Persisting Client");
            session.getTransaction().commit();
            session.close();
        }
        // no need to close factory, try with resource close it automatically

    }

}
