package de.lkor.reference.iso;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.event.EntityDomainEventEventHandler;
import de.lkor.reference.iso.domain.repository.DomainEventRepository;
import de.lkor.reference.iso.service.CountryService;
import de.lkor.reference.iso.service.implementation.CountryServiceImp;
import de.lkor.reference.iso.service.implementation.DomainEventEntityChangeListener;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Bean
    public DomainEventEntityChangeListener domainEventEntityChangeListener(DomainEventRepository domainEventRepository) {
        return new DomainEventEntityChangeListener(domainEventRepository);
    }

    @Bean
    public CountryService countryService(@Autowired DomainEventRepository domainEventRepository) {
        CountryServiceImp countryService = new CountryServiceImp();
        countryService.addEntityChangeListener(domainEventEntityChangeListener(domainEventRepository));

        return countryService;
    }
}
