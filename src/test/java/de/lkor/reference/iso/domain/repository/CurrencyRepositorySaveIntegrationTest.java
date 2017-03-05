package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CurrencyRepositorySaveIntegrationTest extends CurrencyRepositoryIntegrationTestBase {
    @Test
    public void shouldSaveCurrency() {
        // Given
        Currency currency = new Currency("y", "Test 2", "TS2");

        // When
        Currency savedCurrency = currencyRepository.save(currency);

        // Then
        assertThat(savedCurrency.getId(), is(notNullValue()));
    }
}