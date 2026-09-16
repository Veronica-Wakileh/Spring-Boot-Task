package org.exalt.training.springboottask;
import org.exalt.training.springboottask.model.Ticket;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class AppConfiguration {

    @Bean
    public Ticket ticket (){
        return new Ticket();
    }
}