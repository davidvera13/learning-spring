package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.*;

import java.util.HashSet;
import java.util.Set;

public class App01Create {
    public static void main(String[] args) {
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
                    "John",
                    "wayne",
                    "jw@app.com");

            detail = new InstructorDetail(
                    "http://youtube.com",
                    "PHP");

            // create a course
            Course course1 = new Course(
                    "Pacman - How to score one million points");

            Course course2 = new Course("Java");
            Course course3 = new Course("Python");

            // create reviews
            course1.addReview(new Review("Great course"));
            course1.addReview(new Review("Well done"));
            course1.addReview(new Review("What a dumb course! you're an idiot"));

            // associate objects
            course1.setInstructor(instructor);
            course2.setInstructor(instructor);
            course3.setInstructor(instructor);

            instructor.setCourses(Set.of(course1, course2, course3));
            instructor.setInstructorDetail(detail);

            Student s1 = new Student("John", "Dogget", "jd@fbi.com");
            Student s2 = new Student("Monica", "Reyes", "mr@fbi.com");
            Student s3 = new Student("Walter", "Skinner", "ws@fbi.com");

            s1.setCourses(Set.of(course1, course2));
            s2.setCourses(Set.of(course2, course3));
            s3.setCourses(Set.of(course1, course2, course3));

            session = factory.getCurrentSession();
            // creation:: begin transaction
            session.beginTransaction();

            // save everything
            session.persist(instructor);
            session.persist(s1);
            session.persist(s2);
            session.persist(s3);
            long id = instructor.getId();
            session.getTransaction().commit();

            session = factory.getCurrentSession();

            session.beginTransaction();
            // retrieve instructor ...
            Instructor storedInstructor = session.get(Instructor.class, id);
            System.out.println(storedInstructor);

            System.out.println("*********************");
            // we can get instructor detail
            System.out.println(storedInstructor.getInstructorDetail());
            storedInstructor.getCourses().forEach(c -> {
                System.out.println(" course > " + c.getTitle());
                System.out.println(" \treviews");
                c.getReviews().forEach(review -> {
                    System.out.println(" \treview > " + review.getComment());
                });

            });
            System.out.println("*********************");
            Student storedStudent = session.get(Student.class, 3L);
            System.out.println(storedStudent);
            storedStudent.getCourses().forEach(c -> {
                System.out.println("course : " + c.getTitle());
                System.out.println("student instructor : " + c.getInstructor());
            });

            System.out.println();
            session.getTransaction().commit();
        }
    }
}
