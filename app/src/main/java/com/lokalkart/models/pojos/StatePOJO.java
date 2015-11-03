package com.lokalkart.models.pojos;

/**
 * Created by sourin on 02/11/15.
 */
public class StatePOJO {

    private String stateId;
    private String stateName;
    private String stateCode;
    private CountryPOJO country;

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public CountryPOJO getCountry() {
        return country;
    }

    public void setCountry(CountryPOJO country) {
        this.country = country;
    }

    public StatePOJO(String stateId, String stateName, String stateCode, CountryPOJO country) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.stateCode = stateCode;
        this.country = country;
    }

}
