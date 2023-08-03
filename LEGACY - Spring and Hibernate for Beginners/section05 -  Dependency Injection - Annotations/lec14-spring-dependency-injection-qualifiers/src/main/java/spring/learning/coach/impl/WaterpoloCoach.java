package spring.learning.coach.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

@Component
public class WaterpoloCoach implements Coach {
    private FortuneService fortuneService;

    @Override
    public String geDailyWorkout() {
        return "Swim a lot...";
    }

    public WaterpoloCoach() {
        super();
    }

    @Override
    public String getFortune() {
        return fortuneService.getFortune();
    }

    @Autowired
    public String getWaterpoloFortune(@Qualifier("randomFortuneService") FortuneService fortuneService) {
        this.fortuneService = fortuneService;
        return getFortune();

    }
}
