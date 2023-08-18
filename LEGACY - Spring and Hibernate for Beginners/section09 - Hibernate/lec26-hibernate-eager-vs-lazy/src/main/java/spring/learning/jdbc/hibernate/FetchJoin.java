package spring.learning.jdbc.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import spring.learning.jdbc.hibernate.entity.Course;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;

public class FetchJoin {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		try (factory) {
			Session session = factory.getCurrentSession();

			// start transaction
			session.beginTransaction();

			long id = 1;

			// query with HQL
			Query<Instructor> query =
					session.createQuery(
							"SELECT i FROM Instructor i "
									+ "JOIN FETCH i.courses WHERE i.id =:instructorId",
							Instructor.class
					);

			query.setParameter("instructorId", id);
			Instructor instructor = query.getSingleResult();

			System.out.println("# Instructor : " + instructor);


			// commit transaction
			session.getTransaction().commit();

			System.out.println("Session is close but we got results ... because of JOIN FETCH");
			System.out.println("Get courses : " + instructor.getCourses());

			System.out.println("Done");

		}
	}
}
