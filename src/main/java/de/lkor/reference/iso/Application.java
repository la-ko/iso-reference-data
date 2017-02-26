package de.lkor.reference.iso;

import de.lkor.reference.iso.domain.event.EntityBaseEventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by lako on 12.02.2017.
 */
@SpringBootApplication
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    EntityBaseEventHandler countryEventHandler() {
        return new EntityBaseEventHandler();
    }
}
