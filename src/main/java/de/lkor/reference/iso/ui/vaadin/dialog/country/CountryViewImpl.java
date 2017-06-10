package de.lkor.reference.iso.ui.vaadin.dialog.country;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import de.lkor.reference.iso.domain.entity.Country;

import java.util.List;

@SpringComponent
@UIScope
public class CountryViewImpl extends VerticalLayout implements CountryView, View {
    Grid<Country> countryGrid;

    public void init() {
        initializeLayout();

        initializeCountryGrid();

        addCountryGrid();
    }

    private void initializeLayout() {
        setSizeUndefined();
    }

    private void initializeCountryGrid() {
        countryGrid = new Grid<>(Country.class);

        countryGrid.setSizeFull();
        countryGrid.asSingleSelect();
        countryGrid.setColumns("id", "iso3166_1Alpha2Code", "iso3166_1Alpha3Code", "iso3166_1NumericCode",
                "commonName", "formalName", "type", "subType",
                "sovereignty", "ituTTelephoneCode", "ianaCountryCodeTopLevelDomain", "version");
    }

    private void addCountryGrid() {
        addComponent(countryGrid);
    }

    @Override
    public void setCountries(List<Country> countries) {
        countryGrid.setItems(countries);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
