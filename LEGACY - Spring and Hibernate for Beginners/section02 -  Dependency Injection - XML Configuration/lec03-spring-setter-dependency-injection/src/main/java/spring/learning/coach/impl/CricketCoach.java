package spring.learning.coach.impl;

import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

public class CricketCoach implements Coach {
    // define a private field for the dependency
    private FortuneService fortuneService;

    // define a setter for dependency injection
    public void setFortuneService(FortuneService fortuneService) {
        System.out.println("CricketCoach:: inside setter");
        this.fortuneService = fortuneService;
    }

    public CricketCoach() {
        System.out.println("CricketCoach:: inside no arg constructor");
    }

    @Override
    public String geDailyWorkout() {
        return "Practice fast bowling for 15 minutes...";
    }

    @Override
    public String getDailyFortune() {
        return "CricketCoach Coach >> " + fortuneService.getFortune();
    }


}
