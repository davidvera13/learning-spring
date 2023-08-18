package spring.learning.jdbc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.learning.jdbc.hibernate.entity.Course;
import spring.learning.jdbc.hibernate.entity.Instructor;
import spring.learning.jdbc.hibernate.entity.InstructorDetail;

public class EagerLazy {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();


		// eager request : 
		// Hibernate: 
		// select 
		//      instructor0_.id as id1_1_0_, 
		//      instructor0_.email as email2_1_0_,
		// 		instructor0_.first_name as first_na3_1_0_, 
		// 		instructor0_.instructor_detail_id as instruct5_1_0_, 
		// 		instructor0_.last_name as last_nam4_1_0_, 
		// 		courses1_.instructor_id as instruct3_0_1_, 
		// 		courses1_.id as id1_0_1_, courses1_.id as id1_0_2_, 
		// 		courses1_.instructor_id as instruct3_0_2_, 
		// 		courses1_.title as title2_0_2_, 
		// 		instructor2_.id as id1_2_3_,
		// 		instructor2_.hobby as hobby2_2_3_, 
		// 		instructor2_.youtube_channel as youtube_3_2_3_ 
		// from instructor instructor0_ 
		// left outer join course courses1_ on instructor0_.id=courses1_.instructor_id 
		// left outer join instructor_detail instructor2_ on instructor0_.instructor_detail_id=instructor2_.id 
		// where instructor0_.id=?
		//
		// lazy requests :
		// Hibernate: 
		// select 
		// 		instructor0_.id as id1_1_0_, 
		//		instructor0_.email as email2_1_0_, 
		//		instructor0_.first_name as first_na3_1_0_, 
		//		instructor0_.instructor_detail_id as instruct5_1_0_, 
		//		instructor0_.last_name as last_nam4_1_0_, 
		//		instructor1_.id as id1_2_1_, 
		//		instructor1_.hobby as hobby2_2_1_, 
		//		instructor1_.youtube_channel as youtube_3_2_1_ from instructor instructor0_ 
		//		left outer join instructor_detail instructor1_ on instructor0_.instructor_detail_id=instructor1_.id 
		// where instructor0_.id=?
		// 
		// AND 
		// Hibernate: 
		// select 
		// 		courses0_.instructor_id as instruct3_0_0_, 
		// 		courses0_.id as id1_0_0_, 
		// 		courses0_.id as id1_0_1_, 
		// 		courses0_.instructor_id as instruct3_0_1_, 
		// 		courses0_.title as title2_0_1_ 
		// from course courses0_ 
		// where courses0_.instructor_id=?


		try (factory) {
			Session session = factory.getCurrentSession();

			// start transaction
			session.beginTransaction();

			long id = 1;
			Instructor instructor = session.get(Instructor.class, id);

			System.out.println("# Instructor : " + instructor);

			// get courses
			System.out.println("# Courses : " + instructor.getCourses());

			// commit transaction
			session.getTransaction().commit();
			session.close();

			System.out.println("Done");

		}
	}
}
