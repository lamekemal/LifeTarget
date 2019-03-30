package com.archeosbj.lifetarget;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private SearchView mSearchView;
    private boolean mSearchViewVisibility = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_search_api_mtrl_alpha);
        setSupportActionBar(toolbar);

        mSearchView = (SearchView) findViewById(R.id.mSearchview);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (mSearchViewVisibility == false){
                   mSearchView.setVisibility(View.VISIBLE);
                   mSearchViewVisibility = true;
                   toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
               } else if(mSearchViewVisibility == true){
                   mSearchView.setVisibility(View.GONE);
                   mSearchViewVisibility= false;
                   toolbar.setNavigationIcon(R.drawable.abc_ic_search_api_mtrl_alpha);
                   }
            }
        });
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            //define layout
            RelativeLayout ServiceLay = (RelativeLayout) rootView.findViewById(R.id.ServiceLay);
            RelativeLayout AccountLay = (RelativeLayout) rootView.findViewById(R.id.AccontLay);
            RelativeLayout ActuLay = (RelativeLayout) rootView.findViewById(R.id.ActuLay);
            //define default visibility
            ServiceLay.setVisibility(View.GONE);
            AccountLay.setVisibility(View.GONE);
            ActuLay.setVisibility(View.GONE);
             //define tabs page
            if (getArguments().getInt(ARG_SECTION_NUMBER)==1){
                ServiceLay.setVisibility(View.VISIBLE);
                AccountLay.setVisibility(View.GONE);
                ActuLay.setVisibility(View.GONE);
            }else
            if (getArguments().getInt(ARG_SECTION_NUMBER)==3){
                AccountLay.setVisibility(View.VISIBLE);
                ServiceLay.setVisibility(View.GONE);
                ActuLay.setVisibility(View.GONE);
            }else
            if (getArguments().getInt(ARG_SECTION_NUMBER)==2){
                ActuLay.setVisibility(View.VISIBLE);
                ServiceLay.setVisibility(View.GONE);
                AccountLay.setVisibility(View.GONE);
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
