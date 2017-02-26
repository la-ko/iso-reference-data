package de.lkor.reference.iso.domain.event;

import de.lkor.reference.iso.domain.entity.EntityBase;
import de.lkor.reference.iso.domain.repository.DomainEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.util.Date;

@RepositoryEventHandler(EntityBase.class)
public class EntityBaseEventHandler {
    @Autowired
    private DomainEventRepository domainEventRepository;

    @HandleAfterSave
    public void handleAfterSave(EntityBase entity) {
        final DomainEvent domainEvent = assembleDomainEvent(entity, ChangeIndicator.CREATED.UPDATED);

        domainEventRepository.save(domainEvent);
    }

    private DomainEvent assembleDomainEvent(EntityBase entity, ChangeIndicator changeIndicator) {
        return new DomainEvent(entity.getClass().getSimpleName(), entity.getId(), entity.getVersion(), new Date(), changeIndicator);
    }
}
