package spring.learning.coach.impl;

import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;

@Component("cricketCoachBean")
public class CricketCoach implements Coach {
    @Override
    public String geDailyWorkout() {
        return "Practice fast bowling for 15 minutes...";
    }
}
