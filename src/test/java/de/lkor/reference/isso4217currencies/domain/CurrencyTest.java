package de.lkor.reference.isso4217currencies.domain;

import de.lkor.reference.isso4217currencies.domain.Currency;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by lako on 13.02.2017.
 */
public class CurrencyTest {
    @Test
    public void shouldSetCurrency() {
        // Given
        String euro = "Euro";
        Currency currency = new Currency(euro, "EUR", "978");

        // When
        currency.setCurrency(euro);

        //Then
        assertThat(currency.getCurrency(), is(equalTo(euro)));
    }
}