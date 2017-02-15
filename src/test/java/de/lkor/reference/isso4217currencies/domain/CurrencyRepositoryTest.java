package de.lkor.reference.isso4217currencies.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by lako on 13.02.2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CurrencyRepositoryTest {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Before
    public void setUp() {
        Currency currency = new Currency("x", "US Dollar", "USD");
        currencyRepository.save(currency);
    }

    @Test
    public void shouldSaveCurrency() {
        // Given
        Currency currency = new Currency("x", "Euro", "EUR");

        // When
        Currency savedCurrency = currencyRepository.save(currency);

        // Then
        assertThat(savedCurrency.getId(), is(notNullValue()));
    }

    @Test
    public void shouldFindCurrencyByCurrency()
    {
        // Given
        final String currencyName = "US Dollar";

        // When
        final List<Currency> foundCurrencies = currencyRepository.findByCurrency(currencyName);

        // Then
        assertThat(foundCurrencies.get(0).getAlphabeticCode(), is(equalTo("USD")));
    }
}