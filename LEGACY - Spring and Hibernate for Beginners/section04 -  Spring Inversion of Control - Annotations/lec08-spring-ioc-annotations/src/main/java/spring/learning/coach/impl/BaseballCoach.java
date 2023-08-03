package spring.learning.coach.impl;

import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;

// implicit naming
@Component
public class BaseballCoach implements Coach {
    // define a private field for the dependency
    @Override
    public String geDailyWorkout() {
        return "Spend 30 minutes on batting practice";
    }

}
