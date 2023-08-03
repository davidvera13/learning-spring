package spring.learning.coach.impl;

import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;
import spring.learning.service.RandomFortuneService;

public class SwimCoach implements Coach {
    private final FortuneService fortuneService;

    @Override
    public String geDailyWorkout() {
        return "Swim a lot...";
    }

    public SwimCoach(RandomFortuneService randomFortuneService) {
        this.fortuneService = randomFortuneService;
    }

    @Override
    public String getFortune() {
        return fortuneService.getFortune();
    }

}
