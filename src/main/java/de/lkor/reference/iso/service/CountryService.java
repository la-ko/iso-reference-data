package de.lkor.reference.iso.service;

import de.lkor.reference.iso.domain.entity.UpdatedCountryComposite;
import de.lkor.reference.iso.domain.entity.Country;
import org.springframework.stereotype.Service;

public interface CountryService {
    UpdatedCountryComposite updateCountry(Country country);
}
