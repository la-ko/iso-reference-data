package de.lkor.reference.iso.ui.vaadin.dialog.country;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryModel {
    @Autowired
    CountryRepository countryRepository;

    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
