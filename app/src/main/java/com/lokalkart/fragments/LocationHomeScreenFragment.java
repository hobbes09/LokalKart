package com.lokalkart.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.lokalkart.R;
import com.lokalkart.activities.HomeScreen;
import com.lokalkart.models.entities.City;
import com.lokalkart.services.LocationService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnLocationFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LocationHomeScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationHomeScreenFragment extends Fragment implements AdapterView.OnItemSelectedListener, Button.OnClickListener, LoaderManager.LoaderCallbacks<ArrayList<City>> {

    private int width;
    private int height;

    private ArrayList<City> cities;
    private String[] stringCities = {};
    private String[] localities = {"loc1", "loc2", "loc3"};

    private ProgressDialog mProgressDialog;

    private OnLocationFragmentInteractionListener mListener;
    private View fragmentView;

    private LinearLayout llSelectCityLoc;
    private Spinner spinnerCity;
    private Spinner spinnerLocality;
    private ButtonRectangle btnSelectedCityLocality;

    private String selectedCity;
    private String selectedLocality;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment LocationHomeScreenFragment.
     */
    public static LocationHomeScreenFragment newInstance() {
        LocationHomeScreenFragment fragment = new LocationHomeScreenFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    public LocationHomeScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_location_home_screen, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUiElements(view);
    }

    private void initializeUiElements(View fragmentView) {

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        llSelectCityLoc = (LinearLayout)fragmentView.findViewById(R.id.llSelectCityLoc);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)llSelectCityLoc.getLayoutParams();
        int left = Math.round(width/15);
        int top = Math.round(height/20);
        int right = Math.round(width/15);
        int bottom = Math.round(height/20);
        lp.setMargins(left,top,right,bottom);
        llSelectCityLoc.setLayoutParams(lp);

        btnSelectedCityLocality = (ButtonRectangle) fragmentView.findViewById(R.id.btnSelectedCityLocality);
        btnSelectedCityLocality.setOnClickListener(this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getActivity().getResources().getString(R.string.progressDialogCities));
        mProgressDialog.show();
        fetchLocationDataAndStoreInDb();  //  Loader Activity --- time consuming  !!!
        if(mProgressDialog!=null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }


    }

    private void setUpSpinnerData(){
        spinnerCity = (Spinner)fragmentView.findViewById(R.id.spinnerCity);

        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, this.stringCities);

        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCity);
        spinnerCity.setOnItemSelectedListener(this);

        spinnerLocality = (Spinner)fragmentView.findViewById(R.id.spinnerLocality);
        ArrayAdapter<String> adapterLocality = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, this.localities);

        adapterLocality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocality.setAdapter(adapterLocality);
        spinnerLocality.setOnItemSelectedListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLocationFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnLocationFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void fetchLocationDataAndStoreInDb() {

        getActivity().getSupportLoaderManager().initLoader(HomeScreen.mApplicationContext.getResources()
                .getInteger(R.integer.CITY_LIST_LOADER), null, this).forceLoad();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getId() == R.id.spinnerCity){
            selectedCity = parent.getItemAtPosition(position).toString();
            String[] localities = this.localities;
            ArrayAdapter<String> adapterLocality = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, localities);

            adapterLocality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLocality.setAdapter(adapterLocality);
            spinnerLocality.setOnItemSelectedListener(this);
        }
        if(parent.getId() == R.id.spinnerLocality){
            selectedLocality = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        Log.v("location--click", new Timestamp((new Date()).getTime()).toString());
        if(v.getId() == R.id.btnSelectedCityLocality){
//            Toast.makeText(HomeScreen.mApplicationContext, selectedCity + "---" + selectedLocality, Toast.LENGTH_SHORT).show();
            if (mListener != null) {
                Log.v("location--liscall", new Timestamp((new Date()).getTime()).toString());
                mListener.onLocationFragmentInteraction(selectedCity, selectedLocality);
            }
        }
    }

    @Override
    public Loader<ArrayList<City>> onCreateLoader(int id, Bundle args) {

        // Firstly check if cities are already available in DB

        this.cities = new ArrayList<City>();
        LocationLoader mLocationLoader = new LocationLoader(getActivity());
        return mLocationLoader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<City>> loader, ArrayList<City> data) {
        this.cities = data;
        this.stringCities = new String[this.cities.size()];
        for(int index = 0; index < this.cities.size(); index++){
            City city = this.cities.get(index);
            this.stringCities[index] = city.getCityName();
        }
        setUpSpinnerData();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<City>> loader) {
        this.cities = null;
        mProgressDialog = null;
    }


    private static class LocationLoader extends AsyncTaskLoader<ArrayList<City>> {

        public LocationLoader(Context context) {
            super(context);
        }

        @Override
        public ArrayList<City> loadInBackground() {
            LocationService mLocationService = new LocationService();
            return mLocationService.getListOfCities();
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnLocationFragmentInteractionListener {
        public void onLocationFragmentInteraction(String selectedCity, String selectedLocality);
    }

}
