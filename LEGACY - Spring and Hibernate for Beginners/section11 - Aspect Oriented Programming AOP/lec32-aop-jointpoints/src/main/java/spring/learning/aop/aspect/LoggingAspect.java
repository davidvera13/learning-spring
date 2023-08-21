package spring.learning.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import spring.learning.aop.domain.Account;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    // this is where we add all our related aspects from logging
    // Definitions :
    // Aspect:
    //       A modularization of a concern that cuts across multiple objects.
    //       Each aspect focuses on a specific crosscutting functionality
    // Join point:
    //       A point during the execution of a script, such as the execution of
    //       a method or property access
    // Advice:
    //       Action taken by an aspect at a particular join point
    // Pointcut:
    //       A regular expression that matches join points. An advice is associated
    //       with a pointcut expression and runs at any join point that matches
    //       the pointcut

    // this method will be called each time addAccount is called in from any class
    // this method will be called each time addAccount is called in from any class
    // @Before("execution(public void addAccount())")
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice() {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on addAccount()");
    }

    // this method will be called on getAccount() call in AccountDao
    // this method won't be called for MembershipDao
    @Before("execution(public void spring.learning.aop.dao.AccountDao.addAccount())")
    public void beforeAccountDaoAddAccountAdvice() {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on AccountDao.getAccount()");
    }


    // this method will be called on getAccount() call in AccountDao
    // this method won't be called for MembershipDao
    @Before("execution(public void spring.learning.aop.dao.AccountDao.getAccount())")
    public void beforeGetAccountAdvice() {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on AccountDao.getAccount()");
    }

    // this method will be called each time on methods starting with update: updateAccount or updateMembership
    @Before("execution(public void update*())")
    public void beforeUpdateAdvice() {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on any update* methods with void return");
    }

    // this method will be called each time with any return and starting with update
    @Before("execution(* update*())")
    public void beforeUpdateAdviceAnyReturns() {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on any update* methods with any returns");
    }

    // this method will be called on any method that contains add* with account object
    @Before("execution(* add*(spring.learning.aop.domain.Account))")
    public void beforeAddAccountWithParamAdvice() {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on addAccount(account)");
    }

    // this method will be called on any method that contains add* with account object and any other params
    @Before("execution(* add*(spring.learning.aop.domain.Account, ..))")
    public void beforeAddAccountWithParamsAdvice() {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on addAccount(account, isVip, ...)");
    }

    // this method will be called on any method that contains add* with any other params
    @Before("execution(* add*(..))")
    public void beforeAddAcccountWithAnyParamsAdvice() {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on addAccount(..)");
    }

    // #1 * : return type
    // #2 * : class name
    // #3 * : method name
    // will be called at the same time with beforeMethodInPackageCalledAdvice()
    @Before("spring.learning.aop.aspect.AopExpressions.forDaoPackage()")
    public void beforeMethodInPackageCalledAdviceWithPointcut() {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on any DAO methods called(..) using pointcut");
    }

    // will be called at the same time with beforeMethodInPackageCalledAdvice()
    // we pass jointPoint as parameter... it allows to retrieve parameters from method that is intercepted
    @Before("spring.learning.aop.aspect.AopExpressions.forDaoPackage()")
    public void beforeMethodInPackageCalledAdviceWithPointcut(JoinPoint joinPoint) {
        System.out.println("=====>> ORDER 2 : Executing @Before advice on any DAO methods called(..) using pointcut");
        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("\tMethod signature: " + methodSignature);

        // display the method arguments
        Object[] args = joinPoint.getArgs();
        System.out.println("\tMethod arguments: ");
        System.out.println("\t   " + args.length);
        for (Object arg: args) {
            System.out.println("\targ : " + arg );
            if (arg instanceof Account) {
                // downcast and print specific fields
                Account account = (Account) arg;
                System.out.println("\t  - Account name:  " + account.getName());
                System.out.println("\t  - Account level:  " + account.getLevel());
            }
        }
    }
}
