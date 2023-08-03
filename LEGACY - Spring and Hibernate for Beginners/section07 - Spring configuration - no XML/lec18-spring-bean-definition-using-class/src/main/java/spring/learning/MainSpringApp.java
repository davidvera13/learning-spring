package spring.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;
import spring.learning.config.SportConfig;

public class MainSpringApp {
    public static void main(String[] args) {

        //ClassPathXmlApplicationContext ctx =
        //        new ClassPathXmlApplicationContext("applicationContext.xml");
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(SportConfig.class);

        Coach baseballCoach = ctx.getBean("baseballCoach", Coach.class);
        Coach cricketCoach = ctx.getBean("cricketCoachBean", Coach.class);
        Coach tennisCoach = ctx.getBean("tennisCoachBean", Coach.class);
        Coach trackCoach = ctx.getBean("trackCoach", Coach.class);
        Coach waterpoloCoach = ctx.getBean("waterpoloCoach", Coach.class);

        Coach swimCoach = ctx.getBean("swimCoach", Coach.class);

        // constructor dependency injection
        System.out.println(baseballCoach.geDailyWorkout());
        System.out.println(baseballCoach.getFortune());
        System.out.println("****************");

        // setter injection
        System.out.println(cricketCoach.geDailyWorkout());
        System.out.println(cricketCoach.getFortune());
        System.out.println("****************");

        // property injection
        System.out.println(tennisCoach.geDailyWorkout());
        System.out.println(tennisCoach.getFortune());
        System.out.println("****************");

        // constructor dependency injection without annotation
        System.out.println(trackCoach.geDailyWorkout());
        System.out.println(trackCoach.getFortune());
        System.out.println("****************");

        // method injection
        System.out.println(waterpoloCoach.geDailyWorkout());
        System.out.println(waterpoloCoach.getFortune());
        System.out.println("****************");

        // no bean registered with bean declaration in configuration class
        System.out.println(swimCoach.geDailyWorkout());
        System.out.println(swimCoach.getFortune());

        ctx.close();
    }
}
