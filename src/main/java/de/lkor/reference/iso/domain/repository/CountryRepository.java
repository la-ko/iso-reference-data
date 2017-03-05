package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface CountryRepository extends PagingAndSortingRepository<Country, String> {
    List<Country> findByCommonName(@Param("name") String commonName);

    @Override
    @RestResource(exported = false)
    void delete(Country entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Country> entities);

    @Override
    @RestResource(exported = false)
    void delete(String s);
}
