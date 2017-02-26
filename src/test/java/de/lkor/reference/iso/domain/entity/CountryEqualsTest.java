package de.lkor.reference.iso.domain.entity;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class CountryEqualsTest {

    @Test
    public void shouldBeTrueForCountriesWithSameCommonName() {
        // Given
        final Country countryOne = createCountry();
        final Country countryTwo = createCountryWithSameCommonName();

        // When
        final boolean equals = countryOne.equals(countryTwo);

        // Then
        assertThat(equals, is(true));

    }

    @Test
    public void shouldBeFalseForCountriesWithDifferentCommonNames() {
        // Given
        final Country countryOne = createCountry();
        final Country countryTwo = createCountryWithDifferentCommonName();

        // When
        final boolean equals = countryOne.equals(countryTwo);

        // Then
        assertThat(equals, is(false));

    }

    private Country createCountry() {
        return new Country("commonName", new CountryType("Independent State"), "AB");
    }

    private Country createCountryWithSameCommonName() {
        return new Country("commonName", new CountryType("Independent State"), "CD");
    }

    private Country createCountryWithDifferentCommonName() {
        return new Country("commonNameTwo", new CountryType("Independent State"), "AB");
    }
}
