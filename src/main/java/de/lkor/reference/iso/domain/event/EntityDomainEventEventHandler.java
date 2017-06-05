package de.lkor.reference.iso.domain.event;

import de.lkor.reference.iso.domain.entity.ChangeIndicator;
import de.lkor.reference.iso.domain.entity.DomainEvent;
import de.lkor.reference.iso.domain.entity.EntityBase;
import de.lkor.reference.iso.domain.repository.DomainEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.util.Date;

@Slf4j
@RepositoryEventHandler(EntityBase.class)
public class EntityDomainEventEventHandler {
    @Autowired
    private DomainEventRepository domainEventRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @HandleAfterSave
    public void saveDomainEvent(EntityBase entity) {
        final DomainEvent domainEvent = assembleDomainEvent(entity, ChangeIndicator.CREATED.UPDATED);

        final DomainEvent savedDomainEvent = domainEventRepository.save(domainEvent);
        log.info("Saved domain event {}", savedDomainEvent.getId());

        publisher.publishEvent(domainEvent);
    }

    private DomainEvent assembleDomainEvent(EntityBase entity, ChangeIndicator changeIndicator) {
        return new DomainEvent(entity.getClass().getSimpleName(), entity.getId(), entity.getVersion(), new Date(), changeIndicator);
    }
}
