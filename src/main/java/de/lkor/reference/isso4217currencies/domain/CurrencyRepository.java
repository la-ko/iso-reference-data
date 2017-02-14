package de.lkor.reference.isso4217currencies.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lako on 13.02.2017.
 */
@Component
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, BigDecimal> {
    List<Currency> findByCurrency(String currency);
}
