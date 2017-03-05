package de.lkor.reference.iso.domain.event;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"type", "objectId", "objectVersion", "changeIndicator"})
@Entity
// TODO add global version; add UUID
public class DomainEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @NonNull
    @Column(nullable = false)
    private String type;

    @NonNull
    @Column(nullable = false)
    private String objectId;

    @NonNull
    @Column(nullable = false)
    private Long objectVersion;

    @NonNull
    @Column(nullable = false)
    private Date date;

    @NonNull
    @Column(nullable = false)
    private ChangeIndicator changeIndicator;
}
