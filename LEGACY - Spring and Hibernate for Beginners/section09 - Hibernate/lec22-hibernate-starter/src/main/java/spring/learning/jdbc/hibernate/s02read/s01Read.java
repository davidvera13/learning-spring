package spring.learning.jdbc.hibernate.s02read;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Student;

public class s01Read {
    public static void main(String[] args) {
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
            Student student = new Student("Freddy", "Mercury", "fm@queen.com");
            // start transaction
            System.out.println(">> Beginning transaction");
            session.beginTransaction();
            // save student: save is deprecated since hibernate 6
            // session.save(student)
            System.out.println(">> Persisting student");;
            session.persist(student);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Student storedStudent = session.get(Student.class, student.getId());
            System.out.println(storedStudent);
            session.getTransaction().commit();
        }
    }
}
