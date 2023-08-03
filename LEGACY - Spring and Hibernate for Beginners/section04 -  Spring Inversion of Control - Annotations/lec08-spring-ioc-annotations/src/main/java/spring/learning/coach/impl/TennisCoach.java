package spring.learning.coach.impl;

import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;

// explicit component naming
@Component("tennisCoachBean")
public class TennisCoach implements Coach {

    @Override
    public String geDailyWorkout() {
        return "Practice your backend volley";
    }
}
