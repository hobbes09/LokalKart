package com.lokalkart.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.lokalkart.R;
import com.lokalkart.activities.HomeScreen;
import com.lokalkart.managers.CategoryDataAdapterManager;
import com.lokalkart.utils.GlobalConstants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeScreenFragment.OnHomeFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeScreenFragment extends Fragment {

    private OnHomeFragmentInteractionListener mListener;

    private ViewPager vpAds;
    private PagerAdapter mAdScreenSlidePagerAdapter;
    private ImageView ivSearch;
    private ExpandableListView elvCategories;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment HomeScreenFragment.
     */
    public static HomeScreenFragment newInstance() {
        HomeScreenFragment fragment = new HomeScreenFragment();
        return fragment;
    }

    public HomeScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnHomeFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initializeUiElements(view); // DON'T CHANGE ORDER HERE
        customizeActionBar();
        configureAdsViewPager();

        //For setting up category grid-list
        CategoryDataAdapterManager.prepareCategoryDataAdapter(elvCategories);

    }

    private void configureAdsViewPager() {
        mAdScreenSlidePagerAdapter = new ScreenSlidePagerAdapter(HomeScreen.mSupportFragmentManager);
        vpAds.setAdapter(mAdScreenSlidePagerAdapter);
        vpAds.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they
                // are dependent
                // on which page is currently active. An alternative approach is
                // to have each
                // fragment expose actions itself (rather than the activity
                // exposing actions),
                // but for simplicity, the activity provides the actions in this
                // sample.
                Toast.makeText(HomeScreen.mApplicationContext, "On Ad : "+position, Toast.LENGTH_SHORT).show();
                getActivity().invalidateOptionsMenu();
            }
        });
    }

    private void customizeActionBar() {
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater mInflater = LayoutInflater.from(HomeScreen.mApplicationContext);
        View mCustomView = mInflater.inflate(R.layout.actionbar_search, null);
        ivSearch = (ImageView)mCustomView.findViewById(R.id.ivSearch);
        ivSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(HomeScreen.mApplicationContext, "Search tool instantiated", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    private void initializeUiElements(View view) {
        vpAds = (ViewPager)view.findViewById(R.id.vpAds);
        elvCategories = (ExpandableListView)view.findViewById(R.id.elvCategories);

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
    public interface OnHomeFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onHomeFragmentInteraction();
    }


    public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return AdScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return GlobalConstants.NUM_ADS;
        }
    }


}
