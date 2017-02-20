package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;

/**
 * Created by lako on 19.02.2017.
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, BigInteger> {
}
