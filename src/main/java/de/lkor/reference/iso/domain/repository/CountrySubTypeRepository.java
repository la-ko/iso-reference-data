package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.CountrySubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface CountrySubTypeRepository extends JpaRepository<CountrySubType, String> {
}
