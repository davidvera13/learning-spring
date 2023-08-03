package spring.learning.coach.impl;

import org.springframework.stereotype.Component;
import spring.learning.coach.Coach;

@Component
public class TrackCoach implements Coach {
    @Override
    public String geDailyWorkout() {
        return "Run a hard 5km";
    }
}
