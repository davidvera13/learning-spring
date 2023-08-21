package spring.learning.aop.dao;

import org.springframework.stereotype.Component;
import spring.learning.aop.domain.Account;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDao {
    private String name;
    private String serviceCode;

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

    public String getName() {
        System.out.println(getClass() + ": in getName()\n");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": in setName()\n");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": in getServiceCode()\n");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": in setServiceCode()\n");
        this.serviceCode = serviceCode;
    }
    // add a new method: findAccounts
    public List<Account> findAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("john", "Silver"));
        accounts.add(new Account("paul", "Gold"));
        accounts.add(new Account("ringo", "Bronze"));
        accounts.add(new Account("george", "Platinum"));
        return accounts;
    }
}
