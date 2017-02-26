package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Currency;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lako on 26.02.2017.
 */
public class CurrencyRepositoryTestBase {
    @Autowired
    protected CurrencyRepository currencyRepository;

    @Before
    public void setUp() {
        Currency currency = new Currency("x", "Test 1", "TS1");
        currencyRepository.save(currency);
    }
}
