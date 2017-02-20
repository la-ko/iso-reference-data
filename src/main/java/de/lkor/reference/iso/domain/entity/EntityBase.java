package de.lkor.reference.iso.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by lako on 16.02.2017.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@MappedSuperclass
public abstract class EntityBase {
    @NonNull
    @Id
    String id;
}
