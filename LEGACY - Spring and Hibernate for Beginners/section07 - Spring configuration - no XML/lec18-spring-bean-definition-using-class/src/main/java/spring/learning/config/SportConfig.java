package spring.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import spring.learning.coach.impl.SwimCoach;
import spring.learning.service.RandomFortuneService;

@Configuration
@ComponentScan(value = "spring.learning")
@PropertySource("classpath:sport.properties")
public class SportConfig {
    // For old spring version < 4.2
    // add support to resolve ${...} properties
    // @Bean
    // public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceHolderConfigurer() {
    //     return new PropertySourcesPlaceholderConfigurer();
    // }

    // Exception in thread "main"
    // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'swimCoach' available
    @Bean
    public SwimCoach swimCoach() {
        return new SwimCoach(randomFortuneService());
    }

    // we must inject dependency for swimCoach ...
    @Bean
    public RandomFortuneService randomFortuneService() {
        return new RandomFortuneService();
    }
}
