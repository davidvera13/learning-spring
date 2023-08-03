package spring.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;

/**
 * Basic inversion of control using a bean defined in xml
 */
public class MainSpringApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("beanLifecycle-applicationContext.xml");
        // retrieve bean from spring container
        Coach coach1 = ctx.getBean("trackCoachBean", Coach.class);
        System.out.println(coach1.geDailyWorkout());
        System.out.println(coach1.getDailyFortune());

        // singleton
        Coach coach2 = ctx.getBean("tennisCoachBean", Coach.class);
        Coach coach2b = ctx.getBean("tennisCoachBean", Coach.class);
        Coach coach3 = ctx.getBean("cricketCoachBean", Coach.class);
        Coach coach3b = ctx.getBean("cricketCoachBean", Coach.class);
        System.out.println("Singleton ? tennis coach >>> " + coach2.equals(coach2b));
        System.out.println("Singleton ? cricket coach >>> " + coach3.equals(coach3b));
        ctx.close();
    }
}
