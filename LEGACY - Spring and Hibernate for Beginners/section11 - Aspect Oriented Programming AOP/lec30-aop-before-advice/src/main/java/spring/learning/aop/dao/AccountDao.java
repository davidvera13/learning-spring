package spring.learning.aop.dao;

import org.springframework.stereotype.Component;
import spring.learning.aop.domain.Account;

@Component
public class AccountDao {
    public void addAccount() {
        System.out.println(getClass() + " is doing its db work: add customer\n");
    }

    public void addAccount(Account account) {
        System.out.println(getClass() + " is doing its db work: add customer account\n");
    }

    public void addAccount(Account account, boolean isVip) {
        System.out.println(getClass() + " is doing its db work: add customer account + VIP\n");
    }

    public void getAccount() {
        System.out.println(getClass() + " is getting customer details\n");
    }

    public void updateAccount() {
        System.out.println(getClass() + " is updating customer account\n");
    }

    public boolean updateAccountDetails() {
        System.out.println(getClass() + " is updating customer details\n");
        return true;
    }

    public boolean doWork() {
        System.out.println(getClass() + " doWork()\n");
        return true;
    }
}
