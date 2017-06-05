package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.CountryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface CountryTpeRepository extends JpaRepository<CountryType, String> {
}
