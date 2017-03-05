package de.lkor.reference.iso;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.event.EntityDomainEventEventHandler;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EntityDomainEventEventHandler entityDomainEventEventHandler() {
        return new EntityDomainEventEventHandler();
    }

    @Bean
    public MapperFacade mapperFacade() {
        final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Country.class, Country.class).exclude("type").exclude("id").byDefault().register();

        return mapperFactory.getMapperFacade();
    }
}
