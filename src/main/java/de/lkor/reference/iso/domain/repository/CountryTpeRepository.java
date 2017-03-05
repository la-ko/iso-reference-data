package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.CountryType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryTpeRepository extends PagingAndSortingRepository<CountryType, String> {
}
