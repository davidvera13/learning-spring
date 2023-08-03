package spring.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;

public class MainSpringApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach baseballCoach = ctx.getBean("baseballCoach", Coach.class);
        // constructor dependency injection
        System.out.println(baseballCoach.geDailyWorkout());
        System.out.println(baseballCoach.getFortune());
        ctx.close();
    }
}
