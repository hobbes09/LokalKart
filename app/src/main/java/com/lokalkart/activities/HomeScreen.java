package com.lokalkart.activities;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lokalkart.R;
import com.lokalkart.fragments.HomeScreenFragment;
import com.lokalkart.fragments.LoaderHomeScreenFragment;
import com.lokalkart.fragments.LocationHomeScreenFragment;


public class HomeScreen extends AppCompatActivity implements LocationHomeScreenFragment.OnLocationFragmentInteractionListener,
        LoaderHomeScreenFragment.OnLoaderFragmentInteractionListener, HomeScreenFragment.OnHomeFragmentInteractionListener{

    private static boolean set_raw_data;

    private FrameLayout fl_hs_fragment_container;

    public static FragmentManager mSupportFragmentManager;
    public static Context mApplicationContext;
    public static Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initializeUiElements();

        //Launch LocationHomeScreenFragment to download necessary data for this activity
        Fragment mLocationHomeScreenFragment = LocationHomeScreenFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_hs_fragment_container, mLocationHomeScreenFragment)
                .addToBackStack(null)
                .commit();


    }

    private void initializeUiElements() {
        mSupportFragmentManager = getSupportFragmentManager();
        mApplicationContext = getApplicationContext();
        mResources = getResources();
        fl_hs_fragment_container = (FrameLayout)findViewById(R.id.fl_hs_fragment_container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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
    public void onLocationFragmentInteraction(String selectedCity, String selectedLocality) {
        Toast.makeText(HomeScreen.this, "Activity Data::: " + selectedCity + "---" + selectedLocality, Toast.LENGTH_SHORT).show();

        Fragment mLoaderHomeScreenFragment = LoaderHomeScreenFragment.newInstance(selectedCity, selectedLocality);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_hs_fragment_container, mLoaderHomeScreenFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onLoaderFragmentInteraction(boolean status) {

        if(status == true){
            Toast.makeText(HomeScreen.this, "Download complete", Toast.LENGTH_SHORT).show();
            Fragment mHomeScreenFragment = HomeScreenFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_hs_fragment_container, mHomeScreenFragment)
                    .addToBackStack(null)
                    .commit();

        }else{
            Toast.makeText(HomeScreen.this, "Error downloading", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onHomeFragmentInteraction() {

    }
}
