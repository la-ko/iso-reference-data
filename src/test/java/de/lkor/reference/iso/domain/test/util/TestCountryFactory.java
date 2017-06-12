package de.lkor.reference.iso.domain.test.util;

import de.lkor.reference.iso.domain.entity.Country;
import de.lkor.reference.iso.domain.entity.CountryType;
import org.springframework.stereotype.Component;

@Component
public class TestCountryFactory {
    public static final Long COUNTRY_VERSION = 0L;

    public static final String GERMANY_ID = "4765726d616e79";
    public static final String GERMANY_COMMON_NAME = "Germany";
    public static final String GERMANY_FORMAL_NAME = "Federal Republic of Germany";
    public static final String GERMANY_CAPITAL = "Berlin";
    public static final String GERMANY_ITU_T_TELEPHONE_CODE = "49";
    public static final String GERMANY_ISO_3166_1_ALPHA_2_CODE = "DE";
    public static final String GERMANY_ISO_3166_1_ALPHA_3_CODE = "DEU";
    public static final String GERMANY_ISO_3166_1_NUMERIC_CODE = "276";
    public static final String GERMANY_IANA_COUNTRY_CODE_TOP_LEVEL_DOMAIN = ".de";

    private static final CountryType GERMANY_COUNTRY_TYPE = new CountryType("Independent State");

    public Country createGermany() {
        return createGermany(GERMANY_ID, COUNTRY_VERSION, GERMANY_FORMAL_NAME, GERMANY_COUNTRY_TYPE);
    }

    public Country createGermany(String id, Long version, String formalName, CountryType type) {
        Country returnValue = new Country(GERMANY_COMMON_NAME, GERMANY_ISO_3166_1_ALPHA_2_CODE);

        returnValue.setId(id);
        returnValue.setVersion(version);
        returnValue.setFormalName(formalName);
        returnValue.setType(type);

        returnValue.setSubType(null);
        returnValue.setSovereignty(null);
        returnValue.setCapital(GERMANY_CAPITAL);
        returnValue.setItuTTelephoneCode(GERMANY_ITU_T_TELEPHONE_CODE);
        returnValue.setIso3166_1Alpha3Code(GERMANY_ISO_3166_1_ALPHA_3_CODE);
        returnValue.setIso3166_1NumericCode(GERMANY_ISO_3166_1_NUMERIC_CODE);
        returnValue.setIanaCountryCodeTopLevelDomain(GERMANY_IANA_COUNTRY_CODE_TOP_LEVEL_DOMAIN);

        return returnValue;
    }
}
