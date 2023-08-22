package spring.learning.aop;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.learning.aop.config.AppConfig;
import spring.learning.aop.dao.AccountDao;
import spring.learning.aop.domain.Account;


public class MainAfterThrowing {
	public static void main(String[] args) {
		// read spring config file
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		// get the bean from spring container
		AccountDao accountDao = context.getBean("accountDao", AccountDao.class);
		
		// call method to find the accounts
		List<Account> accounts = null;
		
		try {
			// adding a boolean to simulate exception 
			boolean tripWire = true;
			accounts = accountDao.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("\tException was caught: " + e.getLocalizedMessage());
			e.printStackTrace();
		}

		// display the accounts
		System.out.println("Main Program: MainAfterThrowing");
		System.out.println("----");
		
		System.out.println(accounts);
		

		// close context
		context.close();
	}
}
