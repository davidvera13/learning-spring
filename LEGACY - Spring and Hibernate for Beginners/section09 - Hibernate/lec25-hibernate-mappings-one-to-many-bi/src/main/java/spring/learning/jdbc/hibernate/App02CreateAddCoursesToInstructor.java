package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Course;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;

import java.util.HashSet;
import java.util.Set;

public class App02CreateAddCoursesToInstructor {
    public static void main(String[] args) {
        Instructor instructor = new Instructor(
                "John",
                "Wick",
                "jw@app.com");
        InstructorDetail detail = new InstructorDetail(
                "http://youtube.com",
                "violin");

        Set<Course> courses = new HashSet<>();
        // Course course;
        courses.add(new Course("R"));
        courses.add(new Course("Cobol"));

        instructor.setInstructorDetail(detail);


        // try to add with helper and remove it too
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (factory){
            Session session;

            // step 1: save instructor
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.persist(instructor);
            long id = instructor.getId();
            session.getTransaction().commit();

            // step 2! retrieve instructor
            session = factory.getCurrentSession();
            session.beginTransaction();
            Instructor storedInstructor = session.get(Instructor.class, id);
            System.out.println(storedInstructor);

            // step 3: save courses + storedInstructor
            Instructor finalStoredInstructor = storedInstructor;
            session.persist(finalStoredInstructor);
            courses.forEach(c -> c.setInstructor(finalStoredInstructor));
            courses.forEach(session::persist);
            session.getTransaction().commit();

            // step 4: check if updated
            session = factory.getCurrentSession();
            session.beginTransaction();
            storedInstructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
            System.out.println(storedInstructor);

            System.out.println("*********************");
            // we can get instructor detail
            System.out.println(storedInstructor.getInstructorDetail());
            storedInstructor.getCourses().forEach(c -> {
                System.out.println(" course > " + c.getTitle());
            });
        }

    }
}
