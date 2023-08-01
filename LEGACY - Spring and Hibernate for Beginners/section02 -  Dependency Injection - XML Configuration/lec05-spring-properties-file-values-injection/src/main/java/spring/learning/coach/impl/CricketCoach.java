package spring.learning.coach.impl;

import spring.learning.coach.Coach;
import spring.learning.service.FortuneService;

public class CricketCoach implements Coach {
    // define a private field for the dependency
    private FortuneService fortuneService;

    // define litteral values to inject
    private String emailAddress;
    private String team;

    // define a setter for dependency injection
    public void setFortuneService(FortuneService fortuneService) {
        System.out.println("CricketCoach:: inside setter");
        this.fortuneService = fortuneService;
    }
    // getter and setter for private strings
    public void setEmailAddress(String emailAddress) {
        System.out.println("CricketCoach:: inside setter setEmailAddress()");
        this.emailAddress = emailAddress;
    }

    public void setTeam(String team) {
        System.out.println("CricketCoach:: inside setter setTeam()");
        this.team = team;
    }

    // we need getter to access properties
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getTeam() {
        return team;
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
