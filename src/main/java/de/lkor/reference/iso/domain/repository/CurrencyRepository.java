package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Currency;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by lako on 13.02.2017.
 */
@Component
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, String> {
    List<Currency> findByCurrency(String currency);
}
