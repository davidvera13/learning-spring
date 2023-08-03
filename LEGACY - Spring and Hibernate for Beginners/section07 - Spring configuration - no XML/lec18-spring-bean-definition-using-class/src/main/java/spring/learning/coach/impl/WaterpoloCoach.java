package spring.learning.coach.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

@Component
public class WaterpoloCoach implements Coach {
    private FortuneService fortuneService;
    @Value("${app.sport.email}")
    private String email;

    @Value("${app.sport.team}")
    private String team;



    @Override
    public String geDailyWorkout() {
        return "Do learn water polo in swimming pool with your team " + team  + " with contact : " + email;
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
