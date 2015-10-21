package com.possiblelabs.gsontest.json;

import java.util.List;

/**
 * Created by possiblelabs on 10/21/15.
 */
public class CountriesJSON {

    private List<CountryJSON> countries;

    public List<CountryJSON> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryJSON> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "CountriesJSON{" +
                "countries=" + countries +
                '}';
    }
}
