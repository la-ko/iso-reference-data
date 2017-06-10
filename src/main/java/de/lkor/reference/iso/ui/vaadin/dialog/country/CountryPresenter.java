package de.lkor.reference.iso.ui.vaadin.dialog.country;

import com.vaadin.spring.annotation.UIScope;
import de.lkor.reference.iso.domain.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

@VaadinPresenter(viewName="countryView")
public class CountryPresenter extends Presenter<CountryViewImpl> {
    @Autowired
    private CountryModel countryModel;

    @Autowired
    private CountryView countryView;

    private Country currentCountry;

    public void displayCountries() {
        countryView.setCountries(countryModel.findAll());
    }
}
