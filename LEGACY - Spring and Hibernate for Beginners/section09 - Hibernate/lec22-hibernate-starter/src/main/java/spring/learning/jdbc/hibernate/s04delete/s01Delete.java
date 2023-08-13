package spring.learning.jdbc.hibernate.s04delete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Student;

import javax.script.ScriptEngine;

public class s01Delete {
    public static void main(String[] args) {
        // delete by id

        SessionFactory factory = getSession();
        try (factory){
            Student student = new Student("Kirk", "Hammet", "kh@metallica.com");
            Long studentId = create(student);
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            // getting student with id ...
            System.out.println(">> Getting student with id : " + studentId);
            Student storedStudent = session.get(Student.class, studentId);
            // do delete by id
            session.remove(storedStudent);
            // commit transaction
            session.getTransaction().commit();
            // check if deleted
            isDeletedStudent(studentId);

            //******************************************************
            // delete by id
            //******************************************************
            student = new Student("Kirk", "Hammet", "kh@metallica.com");
            studentId = create(student);
            session = factory.getCurrentSession();
            session.beginTransaction();

            // query
            session
                    .createQuery("DELETE FROM Student where id = " + studentId)
                    .executeUpdate();

            // commit transaction
            session.getTransaction().commit();
            // check if deleted
            isDeletedStudent(studentId);

        }


        //Student storedStudent; //  = getByFirstName(student.getFirstName());
        // delete it...
    }

    private static void isDeletedStudent(Long studentId) {
        SessionFactory factory = getSession();
        try (factory) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            // getting student with id ...
            System.out.println(">> Getting student with id : " + studentId);
            Student storedStudent = session.get(Student.class, studentId);
            System.out.println("stored student " + storedStudent);
            session.getTransaction().commit();
        }
    }

    public static Long create(Student student) {
        // create session factory
        SessionFactory factory = getSession();

        // try to persist
        try (factory) {
            // create session
            Session session = factory.getCurrentSession();
            // create student
            System.out.println(">> Creating student");
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
            return student.getId();
        }
    }

    public static Student getByFirstName(String firstName) {
        SessionFactory factory = getSession();
        try (factory){
            Session session = factory.getCurrentSession();
            System.out.println(">> getting student");
            // we know we have just one
            Student student = session
                    .createQuery("from Student s WHERE s.lastName = 'Mercury'", Student.class)
                    .getSingleResult();
            session.getTransaction().commit();
            return student;
        }
    }

    public static SessionFactory getSession() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        return factory;
    }


}
