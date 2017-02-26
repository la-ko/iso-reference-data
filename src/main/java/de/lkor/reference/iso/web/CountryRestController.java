package de.lkor.reference.iso.web;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lako on 21.02.2017.
 */
@RestController
@RequestMapping("/rest/v1/countries")
public class CountryRestController {
    @Autowired
    private CountryRepository countryRepository;

    @RequestMapping(method = RequestMethod.GET)
    List<Country> retrieveCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        countryRepository.findAll().forEach(country -> countries.add(country));

        return countries;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Country lookupCountry(@PathVariable final String id) {
        return countryRepository.findOne(id);
    }
}
