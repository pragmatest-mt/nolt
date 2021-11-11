package com.pragmatest.nolt.gateway.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpStackResponse {
    @JsonProperty("country_name")
    String countryName;

    @JsonProperty("continent_name")
    String continentName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }
}
