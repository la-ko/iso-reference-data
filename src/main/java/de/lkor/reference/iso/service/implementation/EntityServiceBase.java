package de.lkor.reference.iso.service.implementation;

import de.lkor.reference.iso.domain.entity.EntityBase;
import de.lkor.reference.iso.service.event.EntityChangeEvent;
import de.lkor.reference.iso.service.event.EntityChangeListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class EntityServiceBase<E extends EntityBase> {
    private List<EntityChangeListener<E>> listeners = new ArrayList<>();

    public void addEntityChangeListener(EntityChangeListener listener) {
        listeners.add(listener);
    }

    public void removeEntityChangeListener(EntityChangeListener listener) {
        listeners.remove(listener);
    }

    protected void fireEntityChanged(EntityChangeEvent event) {
        log.info("Entity has changed {}", event);

        for (EntityChangeListener listener : listeners) {
            listener.entityChanged(event);
        }
    }
}
