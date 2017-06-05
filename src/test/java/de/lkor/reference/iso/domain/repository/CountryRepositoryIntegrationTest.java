package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CountryRepositoryIntegrationTest {
    @Autowired
    CountryRepository countryRepository;

    @Test
    public void shouldFindCountryByCommonNameStartsWithIgnoringCaseLower() {
        // Given
        String countryNamePrefix = "germ";

        // When
        List<Country> countries = countryRepository.findByCommonNameStartsWithIgnoreCase(countryNamePrefix);

        // Then
        assertCountryCommonNameGermany(countries);
    }

    @Test
    public void shouldFindCountryByCommonNameStartsWithIgnoringCaseUpper() {
        // Given
        String countryNamePrefix = "GERM";

        // When
        List<Country> countries = countryRepository.findByCommonNameStartsWithIgnoreCase(countryNamePrefix);

        // Then
        assertCountryCommonNameGermany(countries);
    }

    private void assertCountryCommonNameGermany(List<Country> countries) {
        assertThat(countries.get(0).getCommonName(), is(equalTo("Germany")));
    }

    @Test
    public void shouldNotFindCountryByCommonNameStartsWithIgnoringCaseBySuffix() {
        // Given
        String countryNameSuffix = "ermany";

        // When
        List<Country> countries = countryRepository.findByCommonNameStartsWithIgnoreCase(countryNameSuffix);

        // Then
        assertCountryNotFound(countries);
    }

    @Test
    public void shouldNotFindCountryByCommonNameStartsWithIgnoringCaseByInfix() {
        // Given
        String countryNameInfix = "erman";

        // When
        List<Country> countries = countryRepository.findByCommonNameStartsWithIgnoreCase(countryNameInfix);

        // Then
        assertCountryNotFound(countries);
    }

    private void assertCountryNotFound(List<Country> countries) {
        assertThat(countries.isEmpty(), is(true));
    }
}
