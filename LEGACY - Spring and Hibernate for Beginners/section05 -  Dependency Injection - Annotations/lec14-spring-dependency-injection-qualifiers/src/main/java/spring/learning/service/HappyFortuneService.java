package spring.learning.service;

import org.springframework.stereotype.Component;

@Component
public class HappyFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        // pick a random string from the array
        return "You're so lucky";
    }
}
