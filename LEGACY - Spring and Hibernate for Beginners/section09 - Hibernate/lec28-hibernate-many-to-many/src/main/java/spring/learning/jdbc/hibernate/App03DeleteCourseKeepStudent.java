package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.*;

public class App03DeleteCourseKeepStudent {
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

            // get course
            Course course = session.get(Course.class, courseId);
            System.out.println("Deleting course ...");
            session.remove(course);

            session.getTransaction().commit();

            // check ?
            System.out.println("********************************");
            session = factory.getCurrentSession();
            // creation:: begin transaction
            session.beginTransaction();
            Student storedStudent = session.get(Student.class, studentId);
            System.out.println("Loaded student: " + storedStudent);
            System.out.println("Course(s)     : " + storedStudent.getCourses());
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
                    "Bilbo",
                    "Baggins",
                    "bb@app.com");

            detail = new InstructorDetail(
                    "http://.com",
                    "Adventure");

            // create a course
            Course course1 = new Course(
                    "Lord of the ring for dummies");

            Course course2 = new Course(
                    "About hobbits");

            // create reviews
            course1.addReview(new Review("Great course indeed"));
            course1.addReview(new Review("Well done!!!!"));
            course1.addReview(new Review("i don't like it like that"));

            course2.addReview(new Review("Not so bad ..."));
            course2.addReview(new Review("Waow!!!!"));

            // associate objects
            course1.setInstructor(instructor);
            course2.setInstructor(instructor);
            instructor.addCourse(course1);
            instructor.addCourse(course2);
            instructor.setInstructorDetail(detail);

            Student student = new Student("Frodo", "Baggins", "fz@fbi.com");
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
