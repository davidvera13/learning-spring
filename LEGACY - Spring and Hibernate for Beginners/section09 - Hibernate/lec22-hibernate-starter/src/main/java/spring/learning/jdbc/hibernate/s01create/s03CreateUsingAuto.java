package spring.learning.jdbc.hibernate.s01create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Administrator;

// this will create table containing
public class s03CreateUsingAuto {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Administrator.class)
                .buildSessionFactory();

        // try to persist
        try (factory) {
            // create session
            Session session = factory.getCurrentSession();
            System.out.println(">> Beginning transaction");
            session.beginTransaction();
            // save student: save is deprecated since hibernate 6
            // session.save(student)
            System.out.println(">> Creating & Persisting Administrators");
            session.persist(new Administrator("John", "Lennon", "john@beatles.com"));
            session.persist(new Administrator("Ringo", "Star", "ringo@beatles.com"));
            session.persist(new Administrator("George", "Harrison", "george@beatles.com"));
            session.persist(new Administrator("Paul", "McCartney", "paul@beatles.com"));
            // commit transaction
            System.out.println(">> Persisting Administrators");
            session.getTransaction().commit();
            session.close();
        }
        // no need to close factory, try with resource close it automatically

    }

}
