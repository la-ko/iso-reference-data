package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lako on 19.02.2017.
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {
    List<Country> findByCommonName(@Param("name") String commonName);
}
