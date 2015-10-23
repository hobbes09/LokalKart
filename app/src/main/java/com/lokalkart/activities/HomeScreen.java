package com.lokalkart.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lokalkart.R;
import com.lokalkart.fragments.LoaderHomeScreenFragment;
import com.lokalkart.fragments.LocationHomeScreenFragment;

public class HomeScreen extends AppCompatActivity implements LocationHomeScreenFragment.OnLocationFragmentInteractionListener, LoaderHomeScreenFragment.OnLoaderFragmentInteractionListener{

    private static boolean set_raw_data;

    private FrameLayout fl_hs_fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        initializeUiElements();

        //Launch LocationHomeScreenFragment to download necessary data for this activity
        Fragment mLocationHomeScreenFragment = LocationHomeScreenFragment.newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fl_hs_fragment_container, mLocationHomeScreenFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();


    }

    private void initializeUiElements() {
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
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fl_hs_fragment_container, mLoaderHomeScreenFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();

    }

    @Override
    public void onLoaderFragmentInteraction(boolean status) {

    }
}
