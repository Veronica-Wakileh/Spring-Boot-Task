package org.exalt.training.springboottask;
import org.exalt.training.springboottask.model.Ticket;
import org.exalt.training.springboottask.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    @ConditionalOnBean(name = "users")
    public Ticket ticket (){
        return new Ticket();
    }

    @Bean
    public User users (){
        return new User();
    }
}