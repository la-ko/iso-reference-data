package de.lkor.reference.iso.service.implementation;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.testutil.TestCountryFactory;
import de.lkor.reference.iso.service.ICountryService;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CountryServiceHasVersionChangedTest {
    @Autowired
    private CountryService objectUnderTest;

    @Autowired
    private TestCountryFactory countryFactory;

    private Country country;

    @Before
    public void initialize() {
        country = countryFactory.createGermany();
    }

    @Test
    public void shouldBeTrueForNonEqualVersions() {
        // Given (initialize)

        // When
        final boolean hasVersionChanged = objectUnderTest.hasVersionChanged(country, country.getVersion() + 1);

        // Then
        assertThat(hasVersionChanged, is(true));
    }

    @Test
    public void shouldBeFalseForEqualVersions() {
        // Given (initialize)

        // When
        final boolean hasVersionChanged = objectUnderTest.hasVersionChanged(country, country.getVersion());

        // Then
        assertThat(hasVersionChanged, is(false));
    }
}
