package spring.learning.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.learning.aop.config.AppConfig;
import spring.learning.aop.dao.AccountDao;
import spring.learning.aop.dao.MembershipDao;
import spring.learning.aop.domain.Account;

public class MainApp {
    public static void main(String[] args) {
        // read spring config file
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // get the bean from spring container
        AccountDao accountDao = context.getBean("accountDao", AccountDao.class);
        MembershipDao membershipDao = context.getBean("membershipDao", MembershipDao.class);

        // call the the business method: the same aspect is called because of signature
        accountDao.addAccount();
        membershipDao.addAccount();

        // creating Account object
        Account account = new Account();
        account.setName("Fox");
        account.setLevel("Platinum");
        accountDao.addAccount(account);

        accountDao.addAccount(account, true);
        accountDao.doWork();


        accountDao.getAccount();
        membershipDao.getAccount();

        accountDao.updateAccount();
        accountDao.updateAccountDetails();
        membershipDao.updateMembership();
        membershipDao.doSomethingElse();

        // calling dao getters and setters
        accountDao.setName("WALT");
        accountDao.setServiceCode("SILVER");
        String name = accountDao.getName();
        String serviceCode = accountDao.getServiceCode();
        System.out.println(name);
        System.out.println(serviceCode);

        // close context
        context.close();
    }
}
