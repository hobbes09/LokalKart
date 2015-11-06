package com.lokalkart.services;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lokalkart.models.entities.City;
import com.lokalkart.models.pojos.CityPOJO;
import com.lokalkart.network.HttpGetClient;
import com.lokalkart.utils.Configurations;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sourin on 20/10/15.
 */
public class LocationService {

    private ArrayList<City> cities;

    public int responseCode;

    public ArrayList<City> getListOfCities(){
        HttpGetClient mHttpGetClient = new HttpGetClient();

        try{

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority(Configurations.baseUrl)
                    .appendPath("city")
                    .appendPath("getAllCity");

            String url = builder.build().toString();
            mHttpGetClient.setUrl(url);
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", "bearer " + Configurations.accessToken);
            mHttpGetClient.setHeaders(headers);

            mHttpGetClient.sendGetRequest();

            if(mHttpGetClient.getResponseCode() == 200){

                this.responseCode = 200;
                String mJsonResp = mHttpGetClient.getResponse();
                JsonReader reader = new JsonReader(new StringReader(mJsonResp));
                reader.setLenient(true);
                Gson gson = new Gson();
                CityPOJO[] arrCityPOJO = gson.fromJson(reader, CityPOJO[].class);

                if(arrCityPOJO.length > 0){
                    this.cities = new ArrayList<>();
                    int index = 0;
                    while(index < arrCityPOJO.length){
                        CityPOJO cityPOJO = arrCityPOJO[index];
                        City city = new City();
                        city.setCityId(cityPOJO.getCityId());
                        city.setCityName(cityPOJO.getCityName());
                        cities.add(city);
                        index++;
                    }
                }
            }else{
                this.cities = null;
                this.responseCode = mHttpGetClient.getResponseCode();
            }

        }catch(Exception e){
            e.printStackTrace();
            this.responseCode = 0;
            this.cities = null;
        }

        return cities;
    }

    public String[] getListOfLocalities(String cityCode){
        String[] localities = {"loc1", "loc2", "loc3"};

        return localities;
    }

}
