package spring.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;

public class MainSpringApp {
    // without qualifiers :
    // Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException:
    // No qualifying bean of type 'spring.learning.service.FortuneService' available:
    // expected single matching bean but found 4: restApiService,happyFortuneService,databaseFortuneService,randomFortuneService
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach baseballCoach = ctx.getBean("baseballCoach", Coach.class);
        Coach cricketCoach = ctx.getBean("cricketCoachBean", Coach.class);
        Coach tennisCoach = ctx.getBean("tennisCoachBean", Coach.class);
        Coach trackCoach = ctx.getBean("trackCoach", Coach.class);
        Coach waterpoloCoach = ctx.getBean("waterpoloCoach", Coach.class);

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

        ctx.close();
    }
}
