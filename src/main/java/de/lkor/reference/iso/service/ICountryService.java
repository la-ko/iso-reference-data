package de.lkor.reference.iso.service;

import de.lkor.reference.iso.domain.entity.UpdatedCountryComposite;
import de.lkor.reference.iso.domain.entity.Country;
import org.springframework.stereotype.Service;

@Service
public interface ICountryService {
    UpdatedCountryComposite updateCountry(Country country);
}
