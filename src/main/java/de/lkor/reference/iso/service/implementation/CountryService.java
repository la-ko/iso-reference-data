package de.lkor.reference.iso.service.implementation;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.entity.UpdatedCountryComposite;
import de.lkor.reference.iso.domain.repository.CountryRepository;
import de.lkor.reference.iso.service.ICountryService;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class CountryService implements ICountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public UpdatedCountryComposite updateCountry(Country country) {
        Country persistentCountry = lookupCountry(country);

        if (persistentCountry == null) {
            return null;
        } else {
            persistentCountry = updateCountry(country, persistentCountry);

            return new UpdatedCountryComposite(hasVersionChanged(persistentCountry, country.getVersion()), persistentCountry);
        }
    }

    boolean hasVersionChanged(Country persistentCountry, Long version) {
        return (persistentCountry.getVersion() != version);
    }

    private Country lookupCountry(Country country) {
        return countryRepository.findOne(country.getId());
    }

    private Country updateCountry(Country inputCountry, Country persistentCountry) {
        log.info("Updating country with id {}", persistentCountry.getId());

        Country mergedCountry = mergeWithInput(inputCountry, persistentCountry);

        return countryRepository.save(mergedCountry);
    }

    private Country mergeWithInput(Country requestCountry, Country persistentCountry) {
        mapperFacade.map(requestCountry, persistentCountry);

        return persistentCountry;
    }
}
