package de.lkor.reference.iso.service.event;

import de.lkor.reference.iso.domain.entity.EntityBase;

public interface EntityChangeListener<E extends EntityBase> {
    void entityChanged(EntityChangeEvent<E> event);
}
