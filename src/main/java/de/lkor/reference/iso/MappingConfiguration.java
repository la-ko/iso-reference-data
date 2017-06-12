package de.lkor.reference.iso;

import de.lkor.reference.iso.domain.entity.Country;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {
    @Bean
    public MapperFacade mapperFacade() {
        final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Country.class, Country.class).exclude("type").exclude("id").byDefault().register();

        return mapperFactory.getMapperFacade();
    }
}