package de.lkor.reference.iso.domain.entity;

import lombok.Getter;
import lombok.Value;

@Value
public class UpdatedCountryComposite {
    private Boolean changed;

    private Country country;
}
