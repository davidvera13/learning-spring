package spring.learning.jdbc.hibernate.s01create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Person;

// this will create table containing
public class s06CreateUsingUUID {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
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
            System.out.println(">> Creating & Persisting Person");
            session.persist(new Person("John", "Lennon", "john@beatles.com"));
            session.persist(new Person("Ringo", "Star", "ringo@beatles.com"));
            session.persist(new Person("George", "Harrison", "george@beatles.com"));
            session.persist(new Person("Paul", "McCartney", "paul@beatles.com"));
            // commit transaction
            System.out.println(">> Persisting Person");
            session.getTransaction().commit();
            session.close();
        }
        // no need to close factory, try with resource close it automatically

    }

}
