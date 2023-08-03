package spring.learning.coach.impl;

import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

public class TrackCoach implements Coach {
    // define a private field for the dependency
    private final FortuneService fortuneService;

    // define a constructor for dependency injection
    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }


    @Override
    public String geDailyWorkout() {
        return "Run a hard 5km";
    }

    @Override
    public String getDailyFortune() {
        return "Track Coach >> " + fortuneService.getFortune();
    }

    private void onInit() {
        System.out.println(">>> TrackCoach onInit() method called");
    }

    private void onDestroy() {
        System.out.println(">>> TrackCoach onDestroy() method called");
    }
}
