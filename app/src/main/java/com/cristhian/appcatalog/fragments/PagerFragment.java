package com.cristhian.appcatalog.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.MainActivity;
import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.adapters.PageAdapter;

/**
 * Created by ctolosa on 02/02/2016.
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
            viewFragments[i] = new MainScreenFragment();
            viewFragments[i].setFragmentCategory(String.valueOf(i));
        }
        mPagerHandler.setAdapter(mPagerAdapter);
        mPagerHandler.setCurrentItem(MainActivity.current_fragment);
        return rootView;
    }


}
