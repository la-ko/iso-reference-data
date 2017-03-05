package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CurrencyRepositoryFindCurrencyByCurrencyIntegrationTest extends CurrencyRepositoryIntegrationTestBase {
    @Test
    public void shouldFindCurrencyByCurrency() {
        // Given
        final String currencyName = "Test 1";

        // When
        final List<Currency> foundCurrencies = currencyRepository.findByCurrency(currencyName);

        // Then
        assertThat(foundCurrencies.get(0).getAlphabeticCode(), is(equalTo("TS1")));
    }
}
