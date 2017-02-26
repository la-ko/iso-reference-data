package de.lkor.reference.iso.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@MappedSuperclass
public abstract class EntityBase {
    @NonNull
    @Id
    String id;

    @Version
    @Column(name = "version", columnDefinition = "BIGINT DEFAULT 0", nullable = false)
    Long version = 0L;
}
