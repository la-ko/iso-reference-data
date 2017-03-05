package de.lkor.reference.iso.service.implementation;

import de.lkor.reference.iso.domain.repository.DomainEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class DomainEventMessageEventListener {
    @Autowired
    private DomainEventRepository domainEventRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publishDomainEventMessage(Object event) {
        log.info("Publishing domain event");
    }
}
