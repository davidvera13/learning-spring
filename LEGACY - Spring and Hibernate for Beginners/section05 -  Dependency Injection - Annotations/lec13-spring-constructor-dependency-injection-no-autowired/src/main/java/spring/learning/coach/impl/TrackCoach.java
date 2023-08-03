package spring.learning.coach.impl;

import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

@Component
public class TrackCoach implements Coach {
    private final FortuneService fortuneService;

    // constructor injection but no "autowired"
    // works because autowired is not mandatory on constructor since spring 4
    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String geDailyWorkout() {
        return "Run a hard 5km";
    }

    @Override
    public String getFortune() {
        return fortuneService.getFortune();
    }
}
