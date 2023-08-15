package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;

public class App03Update {
    public static void main(String[] args) {
        Instructor instructor = new Instructor(
                "Mick",
                "Jagger",
                "mj@app.com");
        InstructorDetail detail = new InstructorDetail(
                "http://youtube.com",
                "C#");

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
            // update: begin transaction
            session.beginTransaction();
            Instructor updatedInstructor = session.get(Instructor.class, id);
            updatedInstructor.setFirstName("John Paul");
            updatedInstructor.setLastName("Bonham");
            updatedInstructor.setEmail("jpb@ledzep.com");
            updatedInstructor.getInstructorDetail().setHobby("Angular");
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            // read:: begin transaction
            session.beginTransaction();
            Instructor storedInstructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
            System.out.println(storedInstructor);

        }
        // factory.close();
    }
}
