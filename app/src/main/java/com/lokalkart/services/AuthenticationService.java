package com.lokalkart.services;

import android.net.Uri;
import android.util.Base64;

import com.lokalkart.models.entities.AuthenticationTokens;
import com.lokalkart.models.pojos.AuthenticationPOJO;
import com.lokalkart.network.HttpPostClient;
import com.lokalkart.utils.GlobalConstants;

import java.util.HashMap;
import com.google.gson.Gson;

/**
 * Created by sourin on 01/11/15.
 */
public class AuthenticationService {

    private String url;
    private String urlParameters;
    private HashMap<String, String> authorizationHeader = new HashMap<String, String>();

    private String response;
    private int responseCode;
    private AuthenticationTokens mAuthenticationTokens;

    public AuthenticationService(AuthenticationTokens tokenObj){
        mAuthenticationTokens = tokenObj;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public AuthenticationTokens getmAuthenticationTokens() {
        return mAuthenticationTokens;
    }

    public boolean fetchAuthenticationToken(){

        GlobalConstants.accessToken = "";
        GlobalConstants.refreshToken = "";
        this.mAuthenticationTokens = new AuthenticationTokens();

        Uri.Builder builder = new Uri.Builder();

        builder.scheme("http")
               .authority(GlobalConstants.baseUrl)
               .appendPath("oauth")
               .appendPath("token")
               .appendQueryParameter("grant_type", "password")
               .appendQueryParameter("scope", "openid")
               .appendQueryParameter("username", GlobalConstants.authorizationUsername)
               .appendQueryParameter("password", GlobalConstants.authorizationPassword);

        this.url = builder.build().toString();
        this.urlParameters = "";
        this.authorizationHeader.put("Authorization", buildBasicAuthorizationString(
                GlobalConstants.userCredentialName, GlobalConstants.userCredentialPassword));

        HttpPostClient mHttpPostClient = new HttpPostClient();
        mHttpPostClient.setUrl(this.url);
        mHttpPostClient.setUrlParameters(this.urlParameters);
        mHttpPostClient.setHeaders(this.authorizationHeader);

        mHttpPostClient.sendPostRequestWithoutPayload();
        this.responseCode = mHttpPostClient.getResponseCode();

        if(mHttpPostClient.getResponseCode() == 200){
            this.response = mHttpPostClient.getResponse();
            Gson gson = new Gson();
            AuthenticationPOJO mAuthenticationPOJO = gson.fromJson(this.response.toString(), AuthenticationPOJO.class);

            this.mAuthenticationTokens.setAccessToken(mAuthenticationPOJO.getAccess_token());
            this.mAuthenticationTokens.setRefreshToken(mAuthenticationPOJO.getRefresh_token());

            GlobalConstants.accessToken = mAuthenticationPOJO.getAccess_token();
            GlobalConstants.refreshToken = mAuthenticationPOJO.getRefresh_token();

            return true;
        }

        return false;
    }

    private static String buildBasicAuthorizationString(String username, String password) {

        String credentials = username + ":" + password;
        return "Basic " + new String(Base64.encode(credentials.getBytes(), Base64.DEFAULT));
    }




}
