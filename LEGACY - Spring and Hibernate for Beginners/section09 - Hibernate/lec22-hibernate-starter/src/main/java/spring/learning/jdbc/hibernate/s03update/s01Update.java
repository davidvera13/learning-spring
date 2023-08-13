package spring.learning.jdbc.hibernate.s03update;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Student;

import java.util.List;

public class s01Update {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // try to persist
        try (factory) {
            // create session & transaction
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            // Get student
            System.out.println("-----------------------");
            System.out.println("Updating student");
            System.out.println(">> Getting student");
            Student student = session.get(Student.class, 1);
            System.out.println("stored: " + student);
            // updating properties
            System.out.println(">> Updating student");
            student.setEmail("scooby@doo.com");
            student.setFirstName("Scooby");
            student.setLastName("Doo");
            // just commit, no persist required
            session.getTransaction().commit();
            System.out.println("updated" + student);

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println(">> Updating student");
            Student storedStudent = session.get(Student.class, 1);
            System.out.println("stored " + storedStudent);

            // update all with query
            //session.createQuery("update Student set email='oops@foo.bar'");
            System.out.println("-----------------------");
            System.out.println("Updating all email students...");
            session
                    .createQuery("UPDATE Student s SET s.email='oops@foo.bar'")
                    .executeUpdate();

            session.getTransaction().commit();


            System.out.println("-----------------------");
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Select * from Student");
            List<Student> studentList;

            studentList = session
                    .createQuery("from Student", Student.class)
                    .getResultList();
            studentList.forEach(System.out::println);
            session.getTransaction().commit();


        }
    }
}
