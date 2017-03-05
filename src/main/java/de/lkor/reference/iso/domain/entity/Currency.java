package de.lkor.reference.iso.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = false, of = {"currency"})
@NoArgsConstructor
@Entity
public class Currency extends EntityBase {
    @Column(unique = true)
    private String currency;

    private String alphabeticCode;

    private String numericCode;

    private String minorUnit;
    private String withdrawalDate;
    private String remark;

    public Currency(final String id, final String currency, final String alphabeticCode) {
        super(id, 0L);
        this.currency = currency;
        this.alphabeticCode = alphabeticCode;
    }
}
