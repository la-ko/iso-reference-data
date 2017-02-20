package de.lkor.reference.iso.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by lako on 16.02.2017.
 */
@Data
@EqualsAndHashCode(callSuper = false, of = {"name"})
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class CountryType extends EntityBase {
    @NonNull
    @Column(nullable = false, unique = true)
    String name;
}
