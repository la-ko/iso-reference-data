package de.lkor.reference.iso.ui.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import de.lkor.reference.iso.ui.vaadin.dialog.country.CountryPresenter;
import de.lkor.reference.iso.ui.vaadin.dialog.country.CountryViewImpl;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {
    @Autowired
    CountryViewImpl countryView;

    @Autowired
    CountryPresenter countryPresenter;

    @Override
    protected void init(VaadinRequest request) {
        initializeCountryView();

        setContent(countryView);

        setVisible(true);
    }

    private void initializeCountryView() {
        countryView.init();
        countryView.setSizeFull();

        countryPresenter.displayCountries();
    }
}
