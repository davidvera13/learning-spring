package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;

public class App02ExceptionHandling {
    public static void main(String[] args) {
        Instructor instructor = new Instructor(
                "John",
                "wayne",
                "jw@app.com");
        InstructorDetail detail = new InstructorDetail(
                "http://youtube.com",
                "PHP");

        instructor.setInstructorDetail(detail);

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = null;
        try {
            // will return NullPointerException
            long id = 1975;

            session = factory.getCurrentSession();
            // read:: begin transaction
            session.beginTransaction();
            // as we have only 1 entry, id in both tables will be the same
            InstructorDetail instructorDetail = session
                    .get(InstructorDetail.class, id);
            session.getTransaction().commit();
            // we also retrieve instructor object
            System.out.println(instructorDetail);

            Instructor currentInstructor = instructorDetail.getInstructor();
            System.out.println(currentInstructor);
            System.out.println("*********************");
            // we can get instructor detail 
            System.out.println(currentInstructor.getInstructorDetail());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
            factory.close();
        }

    }
}
