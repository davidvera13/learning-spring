package spring.learning.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.learning.aop.config.AppConfig;
import spring.learning.aop.dao.AccountDao;
import spring.learning.aop.dao.MembershipDao;
import spring.learning.aop.domain.Account;

import java.util.List;

public class MainAfterReturningApp {
    public static void main(String[] args) {
        // read spring config file
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // get the bean from spring container
        AccountDao accountDao = context.getBean("accountDao", AccountDao.class);

        // call method to find the accounts
        // List<Account> accounts = accountDao.findAccounts();
        System.out.println(accountDao.findAccounts());

        // display the accounts
        System.out.println("Main Program: MainAfterReturning");
        //System.out.println("-----------------------------------------------------------");
        //System.out.println(accounts);


        // close context
        context.close();
    }
}
