package de.lkor.reference.iso.domain.repository;

import de.lkor.reference.iso.domain.entity.DomainEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface DomainEventRepository extends JpaRepository<DomainEvent, BigInteger> {
}
