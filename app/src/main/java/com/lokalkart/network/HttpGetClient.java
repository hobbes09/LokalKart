package com.lokalkart.network;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lokalkart.models.pojos.CityPOJO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sourin on 03/11/15.
 */
public class HttpGetClient {

    private String url;
    private HashMap<String, String> headers = new HashMap<String, String>();

    private int responseCode;
    private String response;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponse() {
        return response;
    }

    public void sendGetRequest(){

        if(this.url != null){
            try{

                URL url = new URL(this.url);
                HttpURLConnection mHttpURLConnection = (HttpURLConnection) url.openConnection();
                mHttpURLConnection.setRequestMethod("GET");
                mHttpURLConnection.setRequestProperty("Content-Type", "application/json");
                mHttpURLConnection.setRequestProperty("Content-Language", "en-US");
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    mHttpURLConnection.setRequestProperty(key, value);
                }
                mHttpURLConnection.connect();

                InputStream mInputStream = mHttpURLConnection.getInputStream();
                // Read the stream
                byte[] b = new byte[1024];
                ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
                while ( mInputStream.read(b) != -1)
                    mByteArrayOutputStream.write(b);

                this.response = new String(mByteArrayOutputStream.toByteArray());

                this.responseCode = mHttpURLConnection.getResponseCode();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
