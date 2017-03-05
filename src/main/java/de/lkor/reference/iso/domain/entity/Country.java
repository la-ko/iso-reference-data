package de.lkor.reference.iso.domain.entity;

import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = false, of = {"commonName"})
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Country extends EntityBase {
    @NonNull
    @Column(nullable = false, unique = true)
    private String commonName;

    @Column(unique = true)
    private String formalName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type")
    private CountryType type;

    @ManyToOne
    @JoinColumn(name = "sub_type")
    private CountrySubType subType;

    @ManyToOne
    @JoinColumn(name = "sovereignty")
    private Country sovereignty;

    private String capital;

    @Column(name = "itu_t_telephone_code")
    private String ituTTelephoneCode;

    @NonNull
    @Column(name = "iso3166_1_alpha_2_code")
    private String iso3166_1Alpha2Code;

    @Column(name = "iso3166_1_alpha_3_code")
    private String iso3166_1Alpha3Code;

    @Column(name = "iso3166_1_numeric_code")
    private String iso3166_1NumericCode;

    private String ianaCountryCodeTopLevelDomain;
}
