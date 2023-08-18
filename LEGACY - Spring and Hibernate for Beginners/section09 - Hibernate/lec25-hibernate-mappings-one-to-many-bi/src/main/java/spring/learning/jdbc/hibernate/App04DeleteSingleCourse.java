package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Course;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class App04DeleteSingleCourse {
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
            AtomicReference<Long> courseId = new AtomicReference<>(0L);
            courses.forEach(c -> c.setInstructor(instructor));
            Session finalSession = session;
            courses.forEach(c -> {
                finalSession.persist(c);
                if(courseId.get() == 0)
                    courseId.set(c.getId());
            });
            courses.forEach(session::persist);
            session.persist(instructor);
            long id = instructor.getId();
            session.getTransaction().commit();

            // step 2: delete course
            session = factory.getCurrentSession();
            session.beginTransaction();
            Course storedCourse = session.get(Course.class, courseId.get());
            System.out.println("Stored course : " + storedCourse);
            session.remove(storedCourse);
            session.getTransaction().commit();

            // step 3: retrieve instructor
            session = factory.getCurrentSession();
            session.beginTransaction();
            Instructor storedInstructor = session.get(Instructor.class, id);
            System.out.println(storedInstructor);

            // step 4: retrieve deleted course (null)
            storedCourse = session.get(Course.class, courseId.get());
            System.out.println(storedCourse);
            session.getTransaction().commit();
        }
    }
}
