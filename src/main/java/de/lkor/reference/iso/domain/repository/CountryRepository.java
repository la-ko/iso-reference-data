package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByCommonName(@Param("name") String commonName);

    List<Country> findByCommonNameStartsWithIgnoreCase(@Param("name") String commonName);
}
