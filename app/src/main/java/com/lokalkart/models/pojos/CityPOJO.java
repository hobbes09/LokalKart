package com.lokalkart.models.pojos;

/**
 * Created by sourin on 02/11/15.
 */
public class CityPOJO {

    private String cityId;
    private String cityName;
    private StatePOJO state;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public StatePOJO getState() {
        return state;
    }

    public void setState(StatePOJO state) {
        this.state = state;
    }

    public CityPOJO(String cityId, String cityName, StatePOJO state) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.state = state;
    }

}
