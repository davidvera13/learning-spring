package spring.learning.coach.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// explicit component naming
@Component("tennisCoachBean")
public class TennisCoach implements Coach {
    @Autowired
    @Qualifier("databaseFortuneService")
    private FortuneService fortuneService;

    public TennisCoach() {
        System.out.println("Constructor called");
    }

    @Override
    public String geDailyWorkout() {
        return "Practice your backend volley";
    }

    @Override
    public String getFortune() {
        return fortuneService.getFortune();
    }

    // init and destroy
    @PostConstruct
    public void onInit() {
        System.out.println(">>>   onInit() called");
    }
    @PreDestroy
    public void onDestroy() {
        System.out.println(">>>   onDestroy() called");
    }
}
