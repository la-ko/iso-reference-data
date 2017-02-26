package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.repository.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CountryRepositoryFindCountryByCommonNameTest {
    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void shouldFindCountryByCommonName() {
        // Given
        final String commonName = "Christmas Island";

        // When
        final List<Country> countries = countryRepository.findByCommonName(commonName);

        // Then
        assertThat(countries.get(0).getCommonName(), is(equalTo("Christmas Island")));
    }
}
