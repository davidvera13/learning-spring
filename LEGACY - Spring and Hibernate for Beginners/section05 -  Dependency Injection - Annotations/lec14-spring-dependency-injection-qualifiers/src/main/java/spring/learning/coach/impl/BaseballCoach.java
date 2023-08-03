package spring.learning.coach.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

// implicit naming
@Component
public class BaseballCoach implements Coach {
    private final FortuneService fortuneService;

    @Autowired
    public BaseballCoach(@Qualifier("happyFortuneService") FortuneService fortuneService) {
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

}
