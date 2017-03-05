package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Currency;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, String> {
    List<Currency> findByCurrency(@Param("currency") String currency);
}
