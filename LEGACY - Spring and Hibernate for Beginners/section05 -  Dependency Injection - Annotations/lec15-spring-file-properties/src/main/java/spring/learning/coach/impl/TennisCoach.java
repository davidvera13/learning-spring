package spring.learning.coach.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

// explicit component naming
@Component("tennisCoachBean")
public class TennisCoach implements Coach {
    @Autowired
    @Qualifier("databaseFortuneService")
    private FortuneService fortuneService;

    @Override
    public String geDailyWorkout() {
        return "Practice your backend volley";
    }

    @Override
    public String getFortune() {
        return fortuneService.getFortune();
    }
}
