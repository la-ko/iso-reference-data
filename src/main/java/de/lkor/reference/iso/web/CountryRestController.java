package de.lkor.reference.iso.web;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.entity.UpdatedCountryComposite;
import de.lkor.reference.iso.domain.entity.ChangeIndicator;
import de.lkor.reference.iso.domain.entity.DomainEvent;
import de.lkor.reference.iso.domain.repository.CountryRepository;
import de.lkor.reference.iso.domain.repository.DomainEventRepository;
import de.lkor.reference.iso.service.CountryService;
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
    private CountryService countryService;

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

        final Country countryWithIdAndVersion = assembleCountryWithIdAndVersion(requestCountry, version, id);
        UpdatedCountryComposite updatedCountryComposite = countryService.updateCountry(countryWithIdAndVersion);

        if (wasCountryFound(updatedCountryComposite)) {
            return handleCountryFound(updatedCountryComposite);
        } else {
            return handleCountryNotFound(id);
        }
    }

    private ResponseEntity<?> handleCountryFound(UpdatedCountryComposite updatedCountryComposite) {
        if (updatedCountryComposite.getChanged()) {
            return handleCountryChanged(updatedCountryComposite.getCountry());
        } else {
            return handleCountryUnchanged(updatedCountryComposite.getCountry().getId());
        }
    }

    private boolean wasCountryFound(UpdatedCountryComposite updatedCountryComposite) {
        return updatedCountryComposite != null;
    }

    private Country assembleCountryWithIdAndVersion(Country requestCountry, Long version, String id) {
        requestCountry.setId(id);
        requestCountry.setVersion(version);

        return requestCountry;
    }

    private ResponseEntity handleCountryNotFound(String id) {
        log.warn("Country with id {} was not found", id);

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity handleCountryUnchanged(String id) {
        log.info("Country with id {} is unchanged", id);

        return ResponseEntity.noContent().build();
    }

    private ResponseEntity handleCountryChanged(Country persistentCountry) {
        log.info("Country with id {} was changed", persistentCountry.getId());

        return ResponseEntity.ok(persistentCountry);
    }
}
