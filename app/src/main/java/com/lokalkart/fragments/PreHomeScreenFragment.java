package com.lokalkart.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.lokalkart.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PreHomeScreenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PreHomeScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreHomeScreenFragment extends Fragment {

    private int width;
    private int height;

    private OnFragmentInteractionListener mListener;

    private LinearLayout llSelectCityLoc;
    private Spinner spinnerCity;
    private Spinner spinnerLocality;

    private static final String[] cities = {"Kolkata", "Bangalore"};
    private static final String[] localities = {"item 1", "item 2", "item 3"};

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PreHomeScreenFragment.
     */
    public static PreHomeScreenFragment newInstance() {
        PreHomeScreenFragment fragment = new PreHomeScreenFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    public PreHomeScreenFragment() {
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
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_pre_home_screen, container, false);
        initializeUiElements(fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initializeUiElements(View fragmentView) {
        llSelectCityLoc = (LinearLayout)fragmentView.findViewById(R.id.llSelectCityLoc);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)llSelectCityLoc.getLayoutParams();
        int left = Math.round(width/15);
        int top = Math.round(height/20);
        int right = Math.round(width/15);
        int bottom = Math.round(height/20);
        lp.setMargins(left,top,right,bottom);
        llSelectCityLoc.setLayoutParams(lp);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
