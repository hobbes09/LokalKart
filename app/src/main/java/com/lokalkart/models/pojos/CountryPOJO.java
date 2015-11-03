package com.lokalkart.models.pojos;

/**
 * Created by sourin on 02/11/15.
 */
public class CountryPOJO {

    private String countryId;
    private String countryName;
    private String countryCode;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public CountryPOJO(String countryId, String countryName, String countryCode) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }
}
