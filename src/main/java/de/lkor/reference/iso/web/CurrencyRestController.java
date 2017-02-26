package de.lkor.reference.iso.web;

import de.lkor.reference.iso.domain.entity.Currency;
import de.lkor.reference.iso.domain.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lako on 14.02.2017.
 */
@RestController
@RequestMapping("/rest/v1/currencies")
public class CurrencyRestController {
    private final CurrencyRepository currencyRepository;

    @Autowired
    CurrencyRestController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Currency> retrieveCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        this.currencyRepository.findAll().forEach(currency -> currencies.add(currency));

        return currencies;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> createCurrency(final Currency currency) {
        final Currency savedCurrency = this.currencyRepository.save(currency);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCurrency.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
