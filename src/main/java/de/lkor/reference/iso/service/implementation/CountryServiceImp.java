package de.lkor.reference.iso.service.implementation;

import de.lkor.reference.iso.domain.entity.ChangeIndicator;
import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.entity.UpdatedCountryComposite;
import de.lkor.reference.iso.domain.repository.CountryRepository;
import de.lkor.reference.iso.service.CountryService;
import de.lkor.reference.iso.service.event.EntityChangeEvent;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Slf4j
public class CountryServiceImp extends EntityServiceBase<Country> implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public UpdatedCountryComposite updateCountry(Country country) {
        Country persistentCountry = lookupCountry(country);

        if (wasCountryFound(persistentCountry)) {
            persistentCountry = updateCountry(country, persistentCountry);

            final boolean hasVersionChanged = hasVersionChanged(persistentCountry, country.getVersion());

            fireEntityChanged(persistentCountry, hasVersionChanged);

            return new UpdatedCountryComposite(hasVersionChanged, persistentCountry);
        } else {
            return null;
        }
    }

    private void fireEntityChanged(Country persistentCountry, boolean hasVersionChanged) {
        if (hasVersionChanged) {
            fireEntityChanged(new EntityChangeEvent(Country.class, persistentCountry.getId(), persistentCountry.getVersion(), new Date(), ChangeIndicator.UPDATED));
        }
    }

    private boolean wasCountryFound(Country persistentCountry) {
        return persistentCountry != null;
    }

    boolean hasVersionChanged(Country persistentCountry, Long version) {
        return (!persistentCountry.getVersion().equals(version));
    }

    private Country lookupCountry(Country country) {
        return countryRepository.findOne(country.getId());
    }

    private Country updateCountry(Country inputCountry, Country persistentCountry) {
        log.info("Updating country with id {}", persistentCountry.getId());

        Country mergedCountry = mergeWithInput(inputCountry, persistentCountry);

        return countryRepository.saveAndFlush(mergedCountry);
    }

    private Country mergeWithInput(Country requestCountry, Country persistentCountry) {
        mapperFacade.map(requestCountry, persistentCountry);

        return persistentCountry;
    }
}
