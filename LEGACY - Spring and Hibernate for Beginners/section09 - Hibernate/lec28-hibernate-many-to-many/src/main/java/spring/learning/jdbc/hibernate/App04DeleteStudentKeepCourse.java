package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.*;

public class App04DeleteStudentKeepCourse {
    private static long studentId;
    private static long courseId;

    public static void main(String[] args) {
        create();
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (factory){
            Session session = factory.getCurrentSession();
            // creation:: begin transaction
            session.beginTransaction();
            Student student = session.get(Student.class, studentId);

            System.out.println("Loaded student: " + student);
            System.out.println("Course(s)     : " + student.getCourses());

            // get Student
            session.remove(student);

            session.getTransaction().commit();

            // check ?
            System.out.println("********************************");
            session = factory.getCurrentSession();
            // creation:: begin transaction
            session.beginTransaction();
            Student storedStudent = session.get(Student.class, studentId);
            System.out.println("Loaded student: " + storedStudent);
            session.getTransaction().commit();
        }
    }

    private static void create() {
        Instructor instructor;
        InstructorDetail detail;

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (factory){
            Session session;


            instructor = new Instructor(
                    "Tim",
                    "Burton",
                    "tbbb@app.com");

            detail = new InstructorDetail(
                    "http://.com",
                    "Movies");

            // create a course
            Course course1 = new Course(
                    "Make nice movies");

            Course course2 = new Course(
                    "How to make Sleepy Hollow");

            // create reviews
            course1.addReview(new Review("Nice learning"));
            course1.addReview(new Review("Good teacher"));

            course2.addReview(new Review("I like it"));
            course2.addReview(new Review("it's great"));

            // associate objects
            course1.setInstructor(instructor);
            course2.setInstructor(instructor);
            instructor.addCourse(course1);
            instructor.addCourse(course2);
            instructor.setInstructorDetail(detail);

            Student student = new Student("Johnny", "Deep", "jd@app.com");
            student.addCourse(course1);
            student.addCourse(course2);

            session = factory.getCurrentSession();
            // creation:: begin transaction
            session.beginTransaction();

            // save everything
            session.persist(instructor);
            session.persist(student);
            studentId = student.getId();
            courseId = course1.getId();
            session.getTransaction().commit();
        }
    }
}