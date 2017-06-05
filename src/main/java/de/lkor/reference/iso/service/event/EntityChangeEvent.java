package de.lkor.reference.iso.service.event;

import de.lkor.reference.iso.domain.entity.ChangeIndicator;
import de.lkor.reference.iso.domain.entity.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EntityChangeEvent<E extends EntityBase> {
    private Class<E> type;

    private String id;

    private Long version;

    private Date date;

    private ChangeIndicator changeIndicator;
}
