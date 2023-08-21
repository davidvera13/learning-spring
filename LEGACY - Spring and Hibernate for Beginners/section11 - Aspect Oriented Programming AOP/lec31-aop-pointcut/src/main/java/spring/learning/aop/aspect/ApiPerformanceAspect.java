package spring.learning.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class ApiPerformanceAspect {
    // point cut is reused ...
    @Before("spring.learning.aop.aspect.AopExpressions.forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("=====>> ORDER 3 : Performing API analytics including getters and setters");
    }

    @Before("spring.learning.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void performApiAnalyticsWithoutGettersAndSetters() {
        System.out.println("=====>> ORDER 3 : Performing API analytics on methods excluding getters and setters");
    }
}
