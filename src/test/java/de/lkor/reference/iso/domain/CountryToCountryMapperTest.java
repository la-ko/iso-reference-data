package de.lkor.reference.iso.domain;

import de.lkor.reference.iso.MappingConfiguration;
import de.lkor.reference.iso.TestSpringConfiguration;
import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.entity.CountryType;
import de.lkor.reference.iso.domain.test.util.TestCountryFactory;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestSpringConfiguration.class, MappingConfiguration.class})
public class CountryToCountryMapperTest {
    @Autowired
    private MapperFacade objectUnderTest;

    @Autowired
    private TestCountryFactory countryFactory;

    // Given
    private Country sourceCountry;
    private Country destinationCountry;

    private static final String SOURCE_COUNTRY_ID = null;
    private static final String DESTINATION_COUNTRY_ID = "1";

    private static final String SOURCE_COUNTRY_FORMAL_NAME = "Fed. Reprivate of Germany";
    private static final String DESTINATION_COUNTRY_FORMAL_NAME = "Federal Reprivate of Germany";

    private static final CountryType SOURCE_COUNTRY_TYPE = null;
    private static final CountryType DESTINATION_COUNTRY_TYPE = new CountryType("Independent State");

    @Before
    public void initialize() {
        sourceCountry = countryFactory.createGermany(SOURCE_COUNTRY_ID, 0L, SOURCE_COUNTRY_FORMAL_NAME, SOURCE_COUNTRY_TYPE);
        destinationCountry = countryFactory.createGermany(DESTINATION_COUNTRY_ID, 0L, DESTINATION_COUNTRY_FORMAL_NAME, DESTINATION_COUNTRY_TYPE);
    }

    @Test
    public void shouldMapCountriesFormalName() {
        // Given initialize()

        // When
        mapCountries();

        // Then
        assertThat(destinationCountry.getFormalName(), is(equalTo(SOURCE_COUNTRY_FORMAL_NAME)));
    }

    @Test
    public void shouldIgnoreId() {
        // Given initialize()

        // When
        mapCountries();

        assertThat(destinationCountry.getId(), is(equalTo(DESTINATION_COUNTRY_ID)));
    }

    @Test
    public void shouldIgnoreType() {
        // Given initialize()

        // When
        mapCountries();

        assertThat(destinationCountry.getType(), is(equalTo(DESTINATION_COUNTRY_TYPE)));
    }

    private void mapCountries() {
        objectUnderTest.map(sourceCountry, destinationCountry);
    }
}
