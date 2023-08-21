package spring.learning.aop.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExpressions {
    // creating a pointcut expression
    @Pointcut("execution(* spring.learning.aop.dao.*.*(..))")
    public void forDaoPackage() {}

    // combine multiple pointcuts
    // ---------------------------------------------------------------------
    // create pointcut for getters
    @Pointcut("execution(* spring.learning.aop.dao.*.get*(..))")
    public void forGettersDaoPackage() {}

    // create pointcut for setterss
    @Pointcut("execution(* spring.learning.aop.dao.*.set*(..))")
    public void forSettersDaoPackage() {}

    // combine : include package & exclude getters and setters
    @Pointcut("forDaoPackage() && !(forGettersDaoPackage() || forSettersDaoPackage())")
    public void forDaoPackageNoGetterSetter() {}

    @Pointcut("forGettersDaoPackage() || forSettersDaoPackage()")
    public void forDaoPackageJustGetterSetter() {}
}

