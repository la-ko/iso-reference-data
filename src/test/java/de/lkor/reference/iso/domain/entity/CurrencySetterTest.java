package de.lkor.reference.iso.domain.entity;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CurrencySetterTest {
    @Test
    public void shouldSetCurrency() {
        // Given
        String euro = "Euro";
        Currency currency = new Currency("x", euro, "EUR");

        // When
        currency.setCurrency(euro);

        //Then
        assertThat(currency.getCurrency(), is(equalTo(euro)));
    }
}