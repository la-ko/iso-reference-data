package de.lkor.reference.isso4217currencies.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;

/**
 * Created by lako on 13.02.2017.
 */

public interface CurrencyRepository extends PagingAndSortingRepository<Currency, BigDecimal> {
    Currency findByCurrency(String currency);
}
