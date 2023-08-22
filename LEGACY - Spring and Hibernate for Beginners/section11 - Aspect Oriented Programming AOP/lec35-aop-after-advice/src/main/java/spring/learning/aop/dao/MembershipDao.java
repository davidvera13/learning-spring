package spring.learning.aop.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDao {
	public void addAccount() {
		System.out.println(getClass() + " is doing its stuffs\n");
	}
	
	public void getAccount() {
		System.out.println(getClass() + " is getting member details\n");
	}

	public void updateMembership() {
		System.out.println(getClass() + " is updating member details\n");
	}
	
	public void doSomethingElse() {
		System.out.println(getClass() + " is doing something else \n");
	}
}
