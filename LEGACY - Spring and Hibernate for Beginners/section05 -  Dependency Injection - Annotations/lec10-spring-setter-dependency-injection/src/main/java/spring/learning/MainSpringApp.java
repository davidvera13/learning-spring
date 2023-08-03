package spring.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;

public class MainSpringApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach baseballCoach = ctx.getBean("baseballCoach", Coach.class);
        Coach cricketCoach = ctx.getBean("cricketCoachBean", Coach.class);

        // constructor dependency injection
        System.out.println(baseballCoach.geDailyWorkout());
        System.out.println(baseballCoach.getFortune());
        System.out.println("****************");

        // setter injection
        System.out.println(cricketCoach.geDailyWorkout());
        System.out.println(cricketCoach.getFortune());
        System.out.println("****************");

        ctx.close();
    }
}
