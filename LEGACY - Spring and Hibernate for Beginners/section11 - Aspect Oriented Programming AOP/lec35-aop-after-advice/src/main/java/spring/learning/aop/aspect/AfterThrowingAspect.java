package spring.learning.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterThrowingAspect {
	
	@AfterThrowing(
			pointcut="execution(* spring.learning.aop.dao.AccountDao.findAccounts(..))",
			throwing="exception"
	)
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint, Throwable exception) {
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n======>>> Executing @AfterThrowing on method : " + method);
		
		// log exception : 
		System.out.println("\n======>>> The exception is : " + exception);
	}



}
