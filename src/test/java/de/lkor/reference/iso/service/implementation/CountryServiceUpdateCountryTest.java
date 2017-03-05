package de.lkor.reference.iso.service.implementation;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.entity.UpdatedCountryComposite;
import de.lkor.reference.iso.domain.repository.CountryRepository;
import de.lkor.reference.iso.domain.testutil.TestCountryFactory;
import de.lkor.reference.iso.service.ICountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CountryServiceUpdateCountryTest {
    private static final String GERMANY_MODIFIED_FORMAL_NAME = "Fed. Republic of Germany";

    @Autowired
    private ICountryService objectUnderTest;

    @MockBean
    private CountryRepository countryRepository;

    @Autowired
    private TestCountryFactory countryFactory;

    private Country country;
    private Country modifiedCountry;
    private Country updatedCountry;

    @Before
    public void initialize() {
        country = countryFactory.createGermany();
        modifiedCountry = countryFactory.createGermany(country.getId(), country.getVersion(), GERMANY_MODIFIED_FORMAL_NAME, country.getType());
        updatedCountry = countryFactory.createGermany(country.getId(), country.getVersion() + 1, GERMANY_MODIFIED_FORMAL_NAME, country.getType());
    }

    @Test
    public void shouldBeNullOnCountryNotFound() {
        // Given
        givenFindReturnsNull();

        // When
        UpdatedCountryComposite updatedCountryComposite = objectUnderTest.updateCountry(country);

        // Then
        assertThat(updatedCountryComposite, is(nullValue()));
    }

    @Test
    public void shouldBeGetChangedFalseForNonUpdatedCountry() {
        // Given
        givenFindOneReturnsCountry();
        givenSaveReturnsCountry();

        // When
        UpdatedCountryComposite updatedCountryComposite = objectUnderTest.updateCountry(countryFactory.createGermany());

        // Then
        assertThat(updatedCountryComposite.getChanged(), is(false));
    }

    @Test
    public void shouldBeGetChangedTrueForUpdatedCountry() {
        // Given
        givenFindOneReturnsCountry();
        givenSaveReturnsUpdatedCountry();

        // When
        UpdatedCountryComposite updatedCountryComposite = objectUnderTest.updateCountry(modifiedCountry);

        // Then
        assertThat(updatedCountryComposite.getChanged(), is(true));
    }

    private void givenFindReturnsNull() {
        given(countryRepository.findOne(any())).willReturn(null);
    }

    private void givenSaveReturnsUpdatedCountry() {
        given(countryRepository.save(modifiedCountry)).willReturn(updatedCountry);
    }

    private void givenSaveReturnsCountry() {
        given(countryRepository.save(modifiedCountry)).willReturn(country);
    }

    private void givenFindOneReturnsCountry() {
        given(countryRepository.findOne(country.getId())).willReturn(country);
    }
}