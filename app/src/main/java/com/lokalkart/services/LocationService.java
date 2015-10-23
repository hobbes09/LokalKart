package com.lokalkart.services;

/**
 * Created by sourin on 20/10/15.
 */
public class LocationService {

    private static LocationService locationService = new LocationService();

    public static LocationService getLocationServiceInstance(){
        return locationService;
    }

    public String[] getListOfCities(){
        String[] cities = {"Kolkata", "Bangalore"};
        return cities;
    }

    public String[] getListOfLocalities(String cityName){
        String[] localities;
        if(cityName == "Bangalore"){
            localities = new String[]{"Koramangala", "JP Nagar", "Jayanagar", "BTM"};
        }else if(cityName == "Kolkata"){
            localities = new String[]{"Gariahat", "SaltLake", "LakeTown", "Alipore"};
        }else{
            localities = new String[]{"loc1", "loc2"};
        }
        return localities;
    }

}
