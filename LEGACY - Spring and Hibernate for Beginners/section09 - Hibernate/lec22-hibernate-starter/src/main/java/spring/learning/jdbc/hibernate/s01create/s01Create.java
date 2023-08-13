package spring.learning.jdbc.hibernate.s01create;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Student;

public class s01Create {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // try to persist
        try (factory) {
            // create session
            Session session = factory.getCurrentSession();
            // create student
            System.out.println(">> Creating student");
            Student student = new Student("Paul", "McCartney", "paul@beatles.com");
            // start transaction
            System.out.println(">> Beginning transaction");
            session.beginTransaction();
            // save student: save is deprecated since hibernate 6
            // session.save(student)
            System.out.println(">> Persisting student");;
            session.persist(student);
            // commit transaction
            System.out.println(">> Persisting student");
            session.getTransaction().commit();
        }
        // no need to close factory, try with resource close it automatically
    }
}
