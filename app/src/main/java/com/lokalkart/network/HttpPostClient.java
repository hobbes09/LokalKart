package com.lokalkart.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sourin on 01/11/15.
 */
public class HttpPostClient {

    private String url;
    private String urlParameters;
    private HashMap<String, String> headers = new HashMap<String, String>();

    private int responseCode;
    private String response;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlParameters() {

        return urlParameters;
    }

    public void setUrlParameters(String urlParameters) {
        this.urlParameters = urlParameters;
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

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void sendPostRequestWithoutPayload(){

        try{
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(urlParameters.getBytes().length));
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                connection.setRequestProperty(key, value);
            }
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //Send request
            DataOutputStream mDataOutputStream = new DataOutputStream (connection.getOutputStream ());
            mDataOutputStream.writeBytes (urlParameters);
            mDataOutputStream.flush ();
            mDataOutputStream.close ();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            this.setResponseCode(connection.getResponseCode());
            this.setResponse(response.toString());


        }catch (Exception e){
            e.printStackTrace();
            Log.v("LokalKart","Exception in sendPostRequestWithoutPayload for "+this.url);
        }

    }

}
