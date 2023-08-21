package spring.learning.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CloudLogAspect {
    @Before("spring.learning.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void logToCloudAsync() {
        System.out.println("=====>> ORDER 1 : Logging to cloud in async fashion");
    }

    @Before("spring.learning.aop.aspect.AopExpressions.forDaoPackageJustGetterSetter()")
    public void logGettersAndSetters() {
        System.out.println("=====>> ORDER 1 : Logging to cloud getters and setters only");
    }
}
