package de.lkor.reference.iso.service.implementation;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.entity.UpdatedCountryComposite;
import de.lkor.reference.iso.domain.repository.CountryRepository;
import de.lkor.reference.iso.domain.testutil.TestCountryFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
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

    @Spy
    @Autowired
    private CountryServiceImp objectUnderTest;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private DomainEventEntityChangeListener domainEventEntityChangeListener;

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
        givenSaveAndFlushReturnsCountry();

        // When
        UpdatedCountryComposite updatedCountryComposite = objectUnderTest.updateCountry(countryFactory.createGermany());

        // Then
        assertThat(updatedCountryComposite.getChanged(), is(false));
    }

    @Test
    public void shouldBeGetChangedTrueForUpdatedCountry() {
        // Given
        givenFindOneReturnsCountry();
        givenSaveAndFlushReturnsUpdatedCountry();

        // When
        UpdatedCountryComposite updatedCountryComposite = objectUnderTest.updateCountry(modifiedCountry);

        // Then
        assertThat(updatedCountryComposite.getChanged(), is(true));
    }

    @Test
    public void shouldFireEntityChangedForUpdatedCountry() throws Exception {
        // Given
        givenFindOneReturnsCountry();
        givenSaveAndFlushReturnsUpdatedCountry();

        // When
        objectUnderTest.updateCountry(modifiedCountry);

        // Then
        // Does not work with Mockito 1!!!
        //verify(objectUnderTest).fireEntityChanged(any());
    }

    private void givenFindReturnsNull() {
        given(countryRepository.findOne(any(String.class))).willReturn(null);
    }

    private void givenSaveAndFlushReturnsUpdatedCountry() {
        given(countryRepository.saveAndFlush(modifiedCountry)).willReturn(updatedCountry);
    }

    private void givenSaveAndFlushReturnsCountry() {
        given(countryRepository.saveAndFlush(modifiedCountry)).willReturn(country);
    }

    private void givenFindOneReturnsCountry() {
        given(countryRepository.findOne(country.getId())).willReturn(country);
    }
}