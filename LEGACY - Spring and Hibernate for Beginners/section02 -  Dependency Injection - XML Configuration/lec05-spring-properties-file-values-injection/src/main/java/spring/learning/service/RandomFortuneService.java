package spring.learning.service;

import java.util.Random;

public class RandomFortuneService implements FortuneService {
    // create an array of strings
    private final String[] data = {
            "Beware of the wolf in sheep's clothing",
            "Diligence is the mother of good luck",
            "The journey is the reward"
    };

    @Override
    public String getFortune() {
        // pick a random string from the array
        return data[new Random().nextInt(data.length)];
    }
}
