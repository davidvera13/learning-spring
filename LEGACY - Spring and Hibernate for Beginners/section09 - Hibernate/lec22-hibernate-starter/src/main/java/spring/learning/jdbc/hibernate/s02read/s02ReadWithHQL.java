package spring.learning.jdbc.hibernate.s02read;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Student;

import java.util.List;

public class s02ReadWithHQL {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // try to persist
        try (factory) {
            Session session = factory.getCurrentSession();
            // start transaction
            session.beginTransaction();
            System.out.println("Select * from Student");
            List<Student> studentList;

            studentList = session
                    .createQuery("from Student", Student.class)
                    .getResultList();
            studentList.forEach(System.out::println);


            System.out.println("-----------------------");
            System.out.println("Select from Student by lastName");
            studentList = session
                    .createQuery("from Student s WHERE s.lastName = 'Mercury'", Student.class)
                    .getResultList();
            studentList.forEach(System.out::println);


            System.out.println("-----------------------");
            System.out.println("Select from Student by lastName or firstName");
            studentList = session
                    .createQuery("from Student s WHERE s.lastName = 'Mercury' OR s.firstName='Paul'", Student.class)
                    .getResultList();
            studentList.forEach(System.out::println);


            System.out.println("-----------------------");
            System.out.println("Select from Student by email like '%beatles%'");
            studentList = session
                    .createQuery("from Student s WHERE s.email LIKE '%beatles%'", Student.class)
                    .getResultList();
            studentList.forEach(System.out::println);


            // commit transaction
            session.getTransaction().commit();
            System.out.println("Completed");

        }
    }
}
