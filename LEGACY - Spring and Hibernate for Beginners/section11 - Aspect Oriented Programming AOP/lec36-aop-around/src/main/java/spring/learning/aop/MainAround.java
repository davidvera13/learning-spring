package spring.learning.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.learning.aop.config.AppConfig;
import spring.learning.aop.service.TrafficPredictionService;

import java.util.logging.Logger;

public class MainAround {
	private static Logger log = Logger.getLogger(MainAround.class.getName());

	public static void main(String[] args) {
		// read spring config file
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);

		// get the bean from spring container
		TrafficPredictionService trafficPredictionService =
				context.getBean("trafficPredictionService", TrafficPredictionService.class);


		log.info("\nMain program: MainAround");
		log.info("Calling getPrediction()");

		String pred = trafficPredictionService.getPrediction();
		log.info("\nPrevision is : " + pred);

		log.info("Finished");

		// close context
		context.close();
	}
}
