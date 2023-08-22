package spring.learning.aop.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import spring.learning.aop.MainAround;


@Aspect
@Component
public class AroundAspect {
	
	private static Logger log = Logger.getLogger(MainAround.class.getName());
	
	// without arguments
	@Around("execution(* spring.learning.aop.service.*.getPrediction())")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		// print out method that is advised
		String method = proceedingJoinPoint.getSignature().toShortString();
		log.info("\n=====>>> Executing @Around on method : " + method);
		// System.out.println("\n=====>>> Executing @Around finally on method : " + method);
		// get beginning timestamp
		Long begin = System.currentTimeMillis();
		
		// execute the method
		Object result = proceedingJoinPoint.proceed();
		
		// get ending timestamp 
		long end = System.currentTimeMillis();
		
		// calculate duration
		long duration = end - begin;
		log.info("\n=====>>> Duration = " + (duration / 1000) + " seconds");
		//System.out.println("\n=====>>> Duration = " + (duration / 1000) + " seconds");
		return result;
	}
	
	// with arguments
	@Around("execution(* spring.learning.aop.service.*.getPrediction(..))")
	public Object aroundGetFortuneWithException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		// print out method that is advised
		String method = proceedingJoinPoint.getSignature().toShortString();
		log.info("\n=====>>> Executing @Around on method : " + method);
		//System.out.println("\n=====>>> Executing @After finally on method : " + method);

		
		// get beginning timestamp
		Long begin = System.currentTimeMillis();
		
		// execute the method
		Object result = null; 
		
		// handling exception here
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception 
			log.warning(e.getLocalizedMessage());
			result = "Major accident... You should catch an helicopter";
			e.printStackTrace();
			// rethrow exception 
			throw e;
		}
		
		// get ending timestamp 
		long end = System.currentTimeMillis();
		
		// calculate duration
		long duration = end - begin;
		log.info("\n=====>>> Duration = " + (duration / 1000) + " seconds");
		//System.out.println("\n=====>>> Duration = " + (duration / 1000) + " seconds");

		return result;
	}
}
