package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.*;

import java.util.Set;

public class App02AddCourseForStudent {
    private static long studentId;

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

            Course course = new Course("Learn to play led zeppelin");
            course.addStudent(student);
            session.persist(course);
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
                    "Jimmy",
                    "Page",
                    "jp@app.com");

            detail = new InstructorDetail(
                    "http://youtube.com",
                    "PHP");

            // create a course
            Course course = new Course(
                    "Learn guitar in 30 days");

            // create reviews
            course.addReview(new Review("Great course"));
            course.addReview(new Review("Well done"));
            course.addReview(new Review("too complex for me"));

            // associate objects
            course.setInstructor(instructor);
            instructor.addCourse(course);
            instructor.setInstructorDetail(detail);

            Student student = new Student("Franck", "Zappa", "fz@fbi.com");
            student.addCourse(course);

            session = factory.getCurrentSession();
            // creation:: begin transaction
            session.beginTransaction();

            // save everything
            session.persist(instructor);
            session.persist(student);
            studentId = student.getId();
            session.getTransaction().commit();
        }
    }
}
