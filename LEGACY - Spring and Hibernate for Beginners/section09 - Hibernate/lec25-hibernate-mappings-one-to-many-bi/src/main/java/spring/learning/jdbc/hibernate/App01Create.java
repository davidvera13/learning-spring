package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Course;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;

import java.util.HashSet;
import java.util.Set;

public class App01Create {
    public static void main(String[] args) {
        Instructor instructor = new Instructor(
                "John",
                "wayne",
                "jw@app.com");
        InstructorDetail detail = new InstructorDetail(
                "http://youtube.com",
                "PHP");

        Set<Course> courses = new HashSet<>();
        // Course course;
        courses.add(new Course("Java"));
        courses.add(new Course("Python"));
        courses.add(new Course("C#"));
        courses.add(new Course("Angular"));
        courses.add(new Course("React"));

        instructor.setInstructorDetail(detail);
        instructor.setCourses(courses);

        // try to add with helper and remove it too
        Course course = new Course("Fortran");
        instructor.addCourse(course);
        instructor.removeCourse(course);
        instructor.addCourse(new Course("C++"));

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (factory){
            Session session;

            session = factory.getCurrentSession();
            // creation:: begin transaction
            session.beginTransaction();

            // step 1: save courses
            courses.forEach(c -> c.setInstructor(instructor));
            courses.forEach(session::persist);

            // step 2: save instructor
            session.persist(instructor);
            long id = instructor.getId();
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            // read:: begin transaction

            session.beginTransaction();
            // retrieve instructor ...
            Instructor storedInstructor = session.get(Instructor.class, id);
            session.getTransaction().commit();

            // Exception in thread "main" org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: spring.learning.jdbc.hibernate.entity.Instructor.courses: could not initialize proxy - no Session
            //	at org.hibernate.collection.spi.AbstractPersistentCollection.throwLazyInitializationException(AbstractPersistentCollection.java:634)
            //	at org.hibernate.collection.spi.AbstractPersistentCollection.withTemporarySessionIfNeeded(AbstractPersistentCollection.java:217)
            //	at org.hibernate.collection.spi.AbstractPersistentCollection.initialize(AbstractPersistentCollection.java:613)
            //	at org.hibernate.collection.spi.AbstractPersistentCollection.read(AbstractPersistentCollection.java:136)
            //	at org.hibernate.collection.spi.PersistentSet.toString(PersistentSet.java:300)
            //	at java.base/java.lang.StringConcatHelper.stringOf(StringConcatHelper.java:453)
            //	at spring.learning.jdbc.hibernate.entity.Instructor.toString(Instructor.java:14)
            //	at java.base/java.lang.String.valueOf(String.java:4215)
            //	at java.base/java.io.PrintStream.println(PrintStream.java:1047)
            System.out.println(storedInstructor);

            System.out.println("*********************");
            // we can get instructor detail
            System.out.println(storedInstructor.getInstructorDetail());
            storedInstructor.getCourses().forEach(c -> {
                System.out.println(" course > " + c.getTitle());
            });
            System.out.println();
        }

    }
}
