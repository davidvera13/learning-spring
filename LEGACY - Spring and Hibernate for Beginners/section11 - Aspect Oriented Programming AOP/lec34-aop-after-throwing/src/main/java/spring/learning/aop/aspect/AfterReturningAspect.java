package spring.learning.aop.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import spring.learning.aop.domain.Account;


@Aspect
@Component
public class AfterReturningAspect {
	
	@AfterReturning(
			pointcut="execution(* spring.learning.aop.dao.AccountDao.findAccounts(..))",
			returning="accounts"
	)
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> accounts) {
		
		// print out method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterReturning on method " + method);
				
		// print out results of the method call
		System.out.println("=====>>> Result is " + accounts);
		
		// modify the data : post processing the data
		// eg. to uppercase ?
		convertAccountNamesToUpperCase(accounts);
		System.out.println("=====>>> Result is " + accounts);
	}

	private void convertAccountNamesToUpperCase(List<Account> accounts) {
		// loop throught accounts
		for (Account account: accounts) {
			// get uppercase
			String name = account.getName().toUpperCase();
			//update the name value
			account.setName(name);
		}
 
		
	}

}
