package spring.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;

/**
 * Basic inversion of control using a bean defined in xml
 */
public class MainSpringApp {
    public static void main(String[] args) {

        // let make this more generic and fully configurable using SPRING IOC
        // > how to change "type" of coach dynamically
        // Step:
        // 1. create a xml file: in resources (as we use maven)
        // 2. load spring configuration
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        // 3. retrieve bean from spring container
        Coach coach1 =  ctx.getBean("baseballCoachBean", Coach.class);
        Coach coach2 =  ctx.getBean("trackCoachBean", Coach.class);
        Coach coach3 =  ctx.getBean("tennisCoachBean", Coach.class);
        Coach coach4 =  ctx.getBean("cricketCoachBean", Coach.class);

        // 4. Call methods on bean
        System.out.println(coach1.geDailyWorkout());
        // 4b. Call method from service (Dependency injection)
        System.out.println(coach1.getDailyFortune());

        System.out.println(coach2.geDailyWorkout());
        System.out.println(coach2.getDailyFortune());

        System.out.println(coach3.geDailyWorkout());
        System.out.println(coach3.getDailyFortune());

        System.out.println(coach4.geDailyWorkout());
        System.out.println(coach4.getDailyFortune());
        // 5. COntext close
        ctx.close();
    }
}
