package spring.learning;

import spring.learning.coach.Coach;
import spring.learning.coach.impl.BaseballCoach;
import spring.learning.coach.impl.CricketCoach;
import spring.learning.coach.impl.TennisCoach;
import spring.learning.coach.impl.TrackCoach;
import spring.learning.service.FortuneService;
import spring.learning.service.RandomFortuneService;

public class Main {
    // old way: it can quickly because cumbersome
    public static void main(String[] args) {
        FortuneService fortuneService = new RandomFortuneService();
        Coach coach1 = new TrackCoach(fortuneService);
        Coach coach2 = new TennisCoach(fortuneService);
        Coach coach3 = new BaseballCoach(fortuneService);
        // very bad practice: we use directly class, and set manually service
        CricketCoach coach4 = new CricketCoach();
        coach4.setFortuneService(new RandomFortuneService());

        System.out.println(coach1.geDailyWorkout());
        System.out.println(coach1.getDailyFortune());

        System.out.println(coach2.geDailyWorkout());
        System.out.println(coach2.getDailyFortune());

        System.out.println(coach3.geDailyWorkout());
        System.out.println(coach3.getDailyFortune());

        System.out.println(coach4.geDailyWorkout());
        // Exception in thread "main" java.lang.NullPointerException:
        // Cannot invoke "spring.learning.service.FortuneService.getFortune()"
        // because "this.fortuneService" is null
        System.out.println(coach4.getDailyFortune());
    }
}
