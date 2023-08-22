package spring.learning.aop.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AfterFinallyAspect {
	
	@After("execution(* spring.learning.aop.dao.AccountDao.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n======>>> Executing @After (finally) on method : " + method);
	}

}
