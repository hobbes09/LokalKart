package com.lokalkart.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lokalkart.R;
import com.lokalkart.models.entities.AuthenticationTokens;
import com.lokalkart.services.AuthenticationService;
import com.lokalkart.utils.GlobalConstants;


/**
 * Created by sourin on 18/10/15.
 */

public class SplashScreen extends AppCompatActivity implements LoaderManager.LoaderCallbacks<AuthenticationTokens>{

    AuthenticationTokens mAuthenticationTokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportLoaderManager().initLoader(getApplicationContext().getResources()
                .getInteger(R.integer.AUTHENTICATION_LOADER), null, this).forceLoad();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<AuthenticationTokens> onCreateLoader(int id, Bundle args) {
        this.mAuthenticationTokens = null;
        AuthenticationLoader mAuthenticationLoader = new AuthenticationLoader(this);
        return mAuthenticationLoader;
    }

    @Override
    public void onLoadFinished(Loader<AuthenticationTokens> loader, AuthenticationTokens data) {
        if(data != null){
            this.mAuthenticationTokens = data;
            Toast.makeText(getApplicationContext(), "Access token : "+ this.mAuthenticationTokens.getAccessToken(), Toast.LENGTH_SHORT).show();
            GlobalConstants.accessToken = this.mAuthenticationTokens.getAccessToken();
            GlobalConstants.refreshToken = this.mAuthenticationTokens.getRefreshToken();

            Intent intent = new Intent(SplashScreen.this, HomeScreen.class);
            startActivity(intent);

        }else{
            Toast.makeText(getApplicationContext(), "Unable to connect to server. Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<AuthenticationTokens> loader) {
        this.mAuthenticationTokens = null;
    }


    private static class AuthenticationLoader extends AsyncTaskLoader<AuthenticationTokens> {

        public AuthenticationLoader(Context context){
            super(context);
        }

        @Override
        public AuthenticationTokens loadInBackground() {
            AuthenticationTokens mAuthenticationTokens = new AuthenticationTokens();
            AuthenticationService mAuthenticationService = new AuthenticationService(mAuthenticationTokens);
            mAuthenticationService.fetchAuthenticationToken();
            if(mAuthenticationService.getResponseCode() == 200){
                return mAuthenticationService.getmAuthenticationTokens();
            }
            return null;
        }
    }

}
