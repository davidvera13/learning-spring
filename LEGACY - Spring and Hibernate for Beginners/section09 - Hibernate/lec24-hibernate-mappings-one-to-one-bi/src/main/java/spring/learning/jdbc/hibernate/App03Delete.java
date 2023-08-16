package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;

public class App03Delete {
    public static void main(String[] args) {
        Instructor instructor = new Instructor(
                "John",
                "Wick",
                "jw@app.com");
        InstructorDetail detail = new InstructorDetail(
                "http:youtube.com",
                "Java");

        instructor.setInstructorDetail(detail);

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        try (factory){
            Session session;

            session = factory.getCurrentSession();
            // creation:: begin transaction
            session.beginTransaction();
            session.persist(instructor);
            long id = instructor.getId();
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            // read:: begin transaction
            session.beginTransaction();
            // as we have only 1 entry, id in both tables will be the same
            InstructorDetail instructorDetail = session
                    .get(InstructorDetail.class, id);
            // remove bidirectional relation (not required if we delete both rows
            instructorDetail.getInstructor().setInstructorDetail(null);

            session.remove(instructorDetail);
            session.getTransaction().commit();

            // check if deleted by query
            session = factory.getCurrentSession();
            // read:: begin transaction
            session.beginTransaction();
            // as we have only 1 entry, id in both tables will be the same
            Instructor storedInstructor = session
                    .get(Instructor.class, id);
            System.out.println(storedInstructor);
            System.out.println(storedInstructor.getInstructorDetail());

        }
        // factory.close();
    }
}
