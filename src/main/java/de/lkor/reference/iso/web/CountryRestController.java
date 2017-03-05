package de.lkor.reference.iso.web;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.event.ChangeIndicator;
import de.lkor.reference.iso.domain.event.DomainEvent;
import de.lkor.reference.iso.domain.repository.CountryRepository;
import de.lkor.reference.iso.domain.repository.DomainEventRepository;
import de.lkor.reference.iso.service.ICountryService;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/rest/v1/countries")
public class CountryRestController {
    @Autowired
    private ICountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DomainEventRepository domainEventRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable final String id, @RequestBody Country requestCountry, @RequestHeader("If-Match") Long version) {
        log.info("Modifying country with id {} of version {}", id, version);
        log.info("{}", requestCountry);

        ResponseEntity returnValue = null;

        Country persistentCountry = lookupCountry(id);

        if (persistentCountry != null) {
            persistentCountry = updateCountry(requestCountry, persistentCountry, version);

            if (hasVersionChanged(persistentCountry, version)) {
                returnValue = handleCountryChanged(persistentCountry);
            } else {
                returnValue = handleCountryUnchanged(id);
            }
        } else {
            returnValue = handleCountryNotFound(id);
        }

        return returnValue;
    }

    private Country lookupCountry(final String id) {
        return countryRepository.findOne(id);
    }

    private Country updateCountry(Country requestCountry, Country persistentCountry, Long version) {
        log.info("Updating country with id {}", persistentCountry.getId());

        Country returnValue = mergeWithInput(persistentCountry, requestCountry, version);
        returnValue = countryRepository.save(returnValue);

        return returnValue;
    }

    private Country mergeWithInput(Country persistentCountry, Country requestCountry, Long version) {
        Country returnValue = persistentCountry;

        mapperFacade.map(requestCountry, returnValue);
        persistentCountry.setVersion(version);

        return returnValue;
    }

    private ResponseEntity handleCountryNotFound(String id) {
        log.warn("Country with id {} was not found", id);

        return ResponseEntity.notFound().build();
    }

    private boolean hasVersionChanged(Country persistentCountry, Long version) {
        return !persistentCountry.getVersion().equals(version);
    }

    private ResponseEntity handleCountryUnchanged(String id) {
        log.info("Country with id {} is unchanged", id);

        return ResponseEntity.noContent().build();
    }

    private ResponseEntity handleCountryChanged(Country persistentCountry) {
        log.info("Country with id {} was changed", persistentCountry.getId());

        createDomainEvent(persistentCountry);

        return ResponseEntity.ok(persistentCountry);
    }

    private void createDomainEvent(Country persistentCountry) {
        final DomainEvent domainEvent = new DomainEvent(persistentCountry.getClass().getSimpleName(), persistentCountry.getId(), persistentCountry.getVersion(), new Date(), ChangeIndicator.UPDATED);
        domainEventRepository.save(domainEvent);
    }
}
