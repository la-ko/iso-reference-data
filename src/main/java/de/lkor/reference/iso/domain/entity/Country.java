package de.lkor.reference.iso.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by lako on 16.02.2017.
 */
// Sort Order,
// Common Name,
// Formal Name,
// Type,
// Sub Type,
// Sovereignty,
// Capital,
// ISO 4217 Currency Code,
// ISO 4217 Currency Name,
// ITU-T Telephone Code,
// ISO 3166-1 2 Letter Code,
// ISO 3166-1 3 Letter Code,
// ISO 3166-1 Number,
// IANA Country Code TLD
@Data
@EqualsAndHashCode(callSuper = true, of = {"commonName"})
@NoArgsConstructor
@Entity
public class Country extends EntityBase {
    @NonNull
    @Column(nullable = false, unique = true)
    private String commonName;

    @Column(unique = true)
    private String formalName;

    @NonNull
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
