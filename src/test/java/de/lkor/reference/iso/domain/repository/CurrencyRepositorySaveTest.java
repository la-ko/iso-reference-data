package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Currency;
import de.lkor.reference.iso.domain.repository.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by lako on 13.02.2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CurrencyRepositorySaveTest extends CurrencyRepositoryTestBase {
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