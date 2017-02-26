package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.event.DomainEvent;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;

public interface DomainEventRepository extends PagingAndSortingRepository<DomainEvent, BigInteger> {
}
