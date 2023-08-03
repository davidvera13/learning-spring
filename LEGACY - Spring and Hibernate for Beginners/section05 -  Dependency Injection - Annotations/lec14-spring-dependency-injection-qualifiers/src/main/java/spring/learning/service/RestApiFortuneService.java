package spring.learning.service;

import org.springframework.stereotype.Component;

@Component
public class RestApiFortuneService implements FortuneService {
    @Override
    public String getFortune() {
        return "Good fortune teller for API";
    }
}
