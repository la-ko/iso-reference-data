package de.lkor.reference.iso.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class EntityBase {
    @Id
    String id;

    @Version
    @Column(name = "version", columnDefinition = "BIGINT DEFAULT 0", nullable = false)
    Long version = 0L;
}
