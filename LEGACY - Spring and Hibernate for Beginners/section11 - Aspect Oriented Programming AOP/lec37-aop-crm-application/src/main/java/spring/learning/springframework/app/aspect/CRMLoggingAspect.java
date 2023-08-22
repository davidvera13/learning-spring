package spring.learning.springframework.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {
    // setup logger
    private Logger log = Logger.getLogger(getClass().getName());

    // setup pointcut declaration
    @Pointcut("execution(* spring.learning.springframework.app.controllers.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* spring.learning.springframework.app.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("execution(* spring.learning.springframework.app.services.*.*(..))")
    private void forServicePackage() {
    }

    // combining pointcuts in a single point cut
    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow() {
    }

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        // display method name
        String method = joinPoint.getSignature().toShortString();
        log.info("\n=====>>> in @Before: calling method " + method);

        // display argument to the method
        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop through and display args
        for(Object arg: args) {
            log.info("\n=====>>> argument " + arg);
        }
    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut  = "forAppFlow()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        // display method name
        String method = joinPoint.getSignature().toShortString();
        log.info("\n=====>>> in @AfterReturning: from method " + method);

        // display returned data
        log.info("\n=====>>> result: " + result);
    }
}
