package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Currency;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class CurrencyRepositoryIntegrationTestBase {
    @Autowired
    protected CurrencyRepository currencyRepository;

    @Before
    public void setUp() {
        Currency currency = new Currency("x", "Test 1", "TS1");
        currencyRepository.save(currency);
    }
}
