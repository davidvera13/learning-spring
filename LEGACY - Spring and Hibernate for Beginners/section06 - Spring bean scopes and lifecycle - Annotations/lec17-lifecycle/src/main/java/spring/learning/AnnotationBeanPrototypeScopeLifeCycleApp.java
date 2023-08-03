package spring.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;

public class AnnotationBeanPrototypeScopeLifeCycleApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach baseballCoach1 = ctx.getBean("baseballCoach", Coach.class);
        Coach baseballCoach2 = ctx.getBean("baseballCoach", Coach.class);

        System.out.println("Is singleton ? >>> " + baseballCoach1.equals(baseballCoach2));
        System.out.println("baseballCoach1 memory location " + baseballCoach1);
        System.out.println("baseballCoach2 memory location " + baseballCoach2);
        ctx.close();
    }
}

