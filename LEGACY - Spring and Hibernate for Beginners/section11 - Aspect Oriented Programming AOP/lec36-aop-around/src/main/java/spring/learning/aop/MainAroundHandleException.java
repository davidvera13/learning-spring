package spring.learning.aop;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.learning.aop.config.AppConfig;
import spring.learning.aop.service.TrafficPredictionService;


public class MainAroundHandleException {

	private static Logger log = Logger.getLogger(MainAroundHandleException.class.getName());

	public static void main(String[] args) {
		// read spring config file
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);

		// get the bean from spring container
		TrafficPredictionService trafficPredictionService =
				context.getBean("trafficPredictionService", TrafficPredictionService.class);


		log.info("\nMain program: MainAround");
		log.info("Calling getPrediction()");

		boolean tripWire = true;
		String pred = trafficPredictionService.getPrediction(tripWire);
		log.info("\nPrevision is : " + pred);

		log.info("Finished");

		// close context
		context.close();
	}
}
