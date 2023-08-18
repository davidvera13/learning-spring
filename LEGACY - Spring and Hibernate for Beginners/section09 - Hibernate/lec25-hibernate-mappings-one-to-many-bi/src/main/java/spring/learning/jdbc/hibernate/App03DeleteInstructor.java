package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Course;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;

import java.util.HashSet;
import java.util.Set;

public class App03DeleteInstructor {
    public static void main(String[] args) {
        Instructor instructor = new Instructor(
                "John",
                "wayne",
                "jw@app.com");
        InstructorDetail detail = new InstructorDetail(
                "http://youtube.com",
                "PHP");

        Set<Course> courses = new HashSet<>();
        courses.add(new Course("Java"));
        courses.add(new Course("Python"));
        courses.add(new Course("C#"));
        courses.add(new Course("Angular"));
        courses.add(new Course("React"));

        instructor.setInstructorDetail(detail);
        instructor.setCourses(courses);

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (factory) {
            Session session;

            // step 1: save courses & instructors
            session = factory.getCurrentSession();
            session.beginTransaction();
            courses.forEach(c -> c.setInstructor(instructor));
            courses.forEach(session::persist);
            session.persist(instructor);
            long id = instructor.getId();
            session.getTransaction().commit();

            // step 2: retrieve instructor & courses
            session = factory.getCurrentSession();
            session.beginTransaction();
            Instructor storedInstructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
            System.out.println(storedInstructor);
            System.out.println("*********************");
            System.out.println(storedInstructor.getInstructorDetail());
            storedInstructor.getCourses().forEach(c -> {
                System.out.println(" course > " + c.getTitle());
            });

            // step 3: delete
            session = factory.getCurrentSession();
            session.beginTransaction();
            Instructor toDelete = session.get(Instructor.class, id);
            toDelete.getCourses().forEach(c -> c.setInstructor(null));
            toDelete.setCourses(null);
            session.remove(toDelete);
            session.getTransaction().commit();

            // step 4: check delete
            session = factory.getCurrentSession();
            session.beginTransaction();
            Instructor deletedInstructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
            System.out.println(deletedInstructor);
        }
    }
}
