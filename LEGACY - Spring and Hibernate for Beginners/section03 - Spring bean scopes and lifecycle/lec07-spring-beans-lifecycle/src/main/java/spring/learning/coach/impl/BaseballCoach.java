package spring.learning.coach.impl;

import org.springframework.beans.factory.DisposableBean;
import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

public class BaseballCoach implements Coach, DisposableBean {
    // define a private field for the dependency
    private final FortuneService fortuneService;

    // define a constructor for dependency injection
    public BaseballCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String geDailyWorkout() {
        return "Spend 30 minutes on batting practice";
    }

    @Override
    public String getDailyFortune() {
        return "Baseball Coach >> " + fortuneService.getFortune();
    }

    private void onInit() {
        System.out.println(">>> Baseball coach onInit() method called");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Baseball coach >> destroy() called from DisposableBean");
    }
}
