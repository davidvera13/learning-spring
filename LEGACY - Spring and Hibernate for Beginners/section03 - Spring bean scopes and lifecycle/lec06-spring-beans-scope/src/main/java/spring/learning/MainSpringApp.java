package spring.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;

/**
 * Basic inversion of control using a bean defined in xml
 */
public class MainSpringApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
        // retrieve bean from spring container
        Coach coach1 = ctx.getBean("trackCoachBean", Coach.class);
        Coach coach2 = ctx.getBean("trackCoachBean", Coach.class);

        Coach coach3 = ctx.getBean("baseballCoachBean", Coach.class);
        Coach coach4 = ctx.getBean("baseballCoachBean", Coach.class);

        // if we have a singleton, we use same instance, so we are using the same memory reference ...
        System.out.println("Using singleton scope \n******************************");
        System.out.println("Are we using same instance ? " + coach1.equals(coach2));
        System.out.println("printing memory location for coach1 " + coach1);
        System.out.println("printing memory location for coach2 " + coach2);
        System.out.println("Using prototype scope \n******************************");
        System.out.println("Are we using same instance ? " + coach3.equals(coach4));
        System.out.println("printing memory location for coach3 " + coach3);
        System.out.println("printing memory location for coach4 " + coach4);

        ctx.close();
    }
}
