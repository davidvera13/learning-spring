package spring.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.learning.coach.Coach;

public class AnnotationBeanSingletonScopeApp {
    // without qualifiers :
    // Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException:
    // No qualifying bean of type 'spring.learning.service.FortuneService' available:
    // expected single matching bean but found 4: restApiService,happyFortuneService,databaseFortuneService,randomFortuneService
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach tennisCoach1 = ctx.getBean("tennisCoachBean", Coach.class);
        Coach tennisCoach2 = ctx.getBean("tennisCoachBean", Coach.class);

        System.out.println("Is singleton ? >>> " + tennisCoach1.equals(tennisCoach2));
        System.out.println("tennisCoach1 memory location " + tennisCoach1);
        System.out.println("tennisCoach2 memory location " + tennisCoach2);
        ctx.close();
    }
}
