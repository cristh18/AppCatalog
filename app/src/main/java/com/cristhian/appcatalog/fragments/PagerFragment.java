package com.cristhian.appcatalog.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.MainActivity;
import com.cristhian.appcatalog.R;

/**
 * Created by yehya khaled on 2/27/2015.
 */
public class PagerFragment extends Fragment {
    public static final int NUM_PAGES = 9;
    public ViewPager mPagerHandler;
    private PageAdapter mPagerAdapter;
    public static MainScreenFragment[] viewFragments = new MainScreenFragment[9];

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pager_fragment, container, false);
        mPagerHandler = (ViewPager) rootView.findViewById(R.id.pager);
        mPagerAdapter = new PageAdapter(getChildFragmentManager(), getActivity());
        for (int i = 0; i < NUM_PAGES; i++) {
            //Date fragmentdate = new Date(System.currentTimeMillis()+((i-2)*86400000));
            //SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
            viewFragments[i] = new MainScreenFragment();
            //viewFragments[i].setFragmentDate(mformat.format(fragmentdate));
            viewFragments[i].setFragmentCategory(String.valueOf(i));
        }
        mPagerHandler.setAdapter(mPagerAdapter);
        mPagerHandler.setCurrentItem(MainActivity.current_fragment);
        return rootView;
    }

    /**
     * Created by ctolosa on 02/02/2016.
     */
    private class PageAdapter extends FragmentStatePagerAdapter {

        public static final int NUM_PAGES = 9;
        Context context;

        public PageAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }


        @Override
        public Fragment getItem(int i) {
            return viewFragments[i];
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return getNamePage(context, position);
        }

        /**
         * @param context
         * @param category
         * @return
         */
        private String getNamePage(Context context, int category) {
            if (category == 0) {
                return "Games";
            } else if (category == 1) {
                return "Social Networking";
            } else if (category == 2) {
                return "Health & Fitness";
            } else if (category == 3) {
                return "Photo & video";
            } else if (category == 4) {
                return "Music";
            } else if (category == 5) {
                return "Education";
            } else if (category == 6) {
                return "Finance";
            } else if (category == 7) {
                return "Entertainment";
            } else if (category == 8) {
                return "Shopping";
            } else {
                return "";
            }
        }
    }


}
