package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Course;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;
import spring.learning.jdbc.hibernate.entity.Review;

import java.util.Set;

public class App04DeleteSingleReview {

    public static long courseId;
    public static void main(String[] args) {
        create();
        SessionFactory factory = getFactory();

        try (factory) {
            Session session;

            session = factory.getCurrentSession();
            session.beginTransaction();
            // retrieve instructor ...
            Course storedCourse = session.get(Course.class, courseId);
            System.out.println(storedCourse.getReviews());

            System.out.println("*********************");
            // delete first review
            Review storedReview = storedCourse.getReviews().stream().findFirst().get();
            storedCourse.getReviews().remove(storedReview);
            session.remove(storedReview);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            storedCourse = session.get(Course.class, courseId);
            System.out.println(storedCourse.getReviews());
            session.getTransaction().commit();

        }
    }

    private static SessionFactory getFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

    }

    public static void create() {
        Instructor instructor;
        InstructorDetail detail;

        SessionFactory factory = getFactory();

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
            Course course = new Course(
                    "Pacman - How to score one million points");


            // create reviews
            course.addReview(new Review("Great course"));
            course.addReview(new Review("Well done"));
            course.addReview(new Review("What a dumb course! you're an idiot"));
            // associate objects
            course.setInstructor(instructor);
            instructor.setCourses(Set.of(course));
            instructor.setInstructorDetail(detail);

            session = factory.getCurrentSession();
            // creation:: begin transaction
            session.beginTransaction();

            // save everything
            session.persist(instructor);
            courseId = instructor.getCourses().stream().findFirst().get().getId();
            session.getTransaction().commit();
        }
    }
}
