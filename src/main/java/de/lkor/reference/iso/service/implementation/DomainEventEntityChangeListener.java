package de.lkor.reference.iso.service.implementation;

import de.lkor.reference.iso.domain.entity.ChangeIndicator;
import de.lkor.reference.iso.domain.entity.DomainEvent;
import de.lkor.reference.iso.domain.repository.DomainEventRepository;
import de.lkor.reference.iso.service.event.EntityChangeEvent;
import de.lkor.reference.iso.service.event.EntityChangeListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.Date;

@Slf4j
@AllArgsConstructor
@Transactional(Transactional.TxType.MANDATORY)
public class DomainEventEntityChangeListener implements EntityChangeListener {
    private DomainEventRepository domainEventRepository;

    @Override
    public void entityChanged(EntityChangeEvent event) {
        log.info("Creating domain event for entity change {}", event);

        DomainEvent domainEvent = new DomainEvent(event.getType().getSimpleName(), event.getId(), event.getVersion(), new Date(), ChangeIndicator.UPDATED);
        domainEventRepository.save(domainEvent);
    }
}
