package spring.learning.coach.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

@Component("cricketCoachBean")
public class CricketCoach implements Coach {
    private FortuneService fortuneService;

    // setter injection
    @Autowired
    public void setFortuneService(@Qualifier("restApiFortuneService") FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String geDailyWorkout() {
        return "Practice fast bowling for 15 minutes...";
    }

    @Override
    public String getFortune() {
        return fortuneService.getFortune();
    }
}
