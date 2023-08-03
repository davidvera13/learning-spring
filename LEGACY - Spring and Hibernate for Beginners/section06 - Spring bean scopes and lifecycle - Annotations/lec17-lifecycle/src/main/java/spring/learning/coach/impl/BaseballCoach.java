package spring.learning.coach.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// implicit naming
@Component
@Scope("prototype")
public class BaseballCoach implements Coach, DisposableBean {
    private final FortuneService fortuneService;

    @Autowired
    public BaseballCoach(@Qualifier("happyFortuneService") FortuneService fortuneService) {
        System.out.println("Constructor called");
        this.fortuneService = fortuneService;
    }

    @Override
    public String geDailyWorkout() {
        return "Spend 30 minutes on batting practice";
    }

    @Override
    public String getFortune() {
        return fortuneService.getFortune();
    }

    @PostConstruct
    public void onInit() {
        System.out.println(">>>   onInit() called");
    }
    @PreDestroy
    public void onDestroy() {
        System.out.println(">>>   onDestroy() called");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(">> destroy called()");

    }

}
