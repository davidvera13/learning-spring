package spring.learning.aop.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficPredictionService {

	public String getPrediction() {	
		// simulate a delay
		try {
			TimeUnit.SECONDS.sleep(5);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return a fortune
		return "Expect heavy traffic this morning";
	}
	
	public String getPrediction(boolean tripWire) {	
		// simulate a delay
		if (tripWire) {
			throw new RuntimeException("You crashed the application");
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return a fortune
		return "Expect heavy traffic this morning";
	}
}
