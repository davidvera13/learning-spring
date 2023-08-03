package spring.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;

public class MainSpringApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Coach tennisCoach = ctx.getBean("tennisCoachBean", Coach.class);
        Coach baseballCoach = ctx.getBean("baseballCoach", Coach.class);
        Coach cricketCoach = ctx.getBean("cricketCoachBean", Coach.class);
        Coach trackCoach = ctx.getBean("trackCoach", Coach.class);

        System.out.println(tennisCoach.geDailyWorkout());
        System.out.println(baseballCoach.geDailyWorkout());
        System.out.println(cricketCoach.geDailyWorkout());
        System.out.println(trackCoach.geDailyWorkout());
        ctx.close();
    }
}
