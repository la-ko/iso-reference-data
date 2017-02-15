package de.lkor.reference.isso4217currencies.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by lako on 12.02.2017.
 */
public
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
class Currency {
    @NonNull
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NonNull
    private String currency;

    @NonNull
    private String alphabeticCode;

    private String numericCode;

    private String minorUnit;
    private String withdrawalDate;
    private String remark;
}
