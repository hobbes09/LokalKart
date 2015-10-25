package com.lokalkart.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lokalkart.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoaderHomeScreenFragment.OnLoaderFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoaderHomeScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaderHomeScreenFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CITY = "SelectedCity";
    private static final String ARG_LOCALITY = "SelectedLocality";

    // TODO: Rename and change types of parameters
    private String selectedCity;
    private String selectedLocality;
    private boolean status = true;

    private OnLoaderFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param city -> selectedCity .
     * @param locality -> selectedLocality.
     * @return A new instance of fragment LoaderHomeScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoaderHomeScreenFragment newInstance(String city, String locality) {
        LoaderHomeScreenFragment fragment = new LoaderHomeScreenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, city);
        args.putString(ARG_LOCALITY, locality);
        fragment.setArguments(args);
        return fragment;
    }

    public LoaderHomeScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedCity = getArguments().getString(ARG_CITY);
            selectedLocality = getArguments().getString(ARG_LOCALITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loader_home_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (mListener != null) {
            mListener.onLoaderFragmentInteraction(status);
        }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLoaderFragmentInteractionListener) activity;
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
    public interface OnLoaderFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onLoaderFragmentInteraction(boolean status);
    }

}
