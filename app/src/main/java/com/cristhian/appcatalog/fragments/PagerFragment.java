package com.cristhian.appcatalog.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.MainActivity;
import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.adapters.PageAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yehya khaled on 2/27/2015.
 */
public class PagerFragment extends Fragment
{
    public static final int NUM_PAGES = 9;
    public ViewPager mPagerHandler;
    private PageAdapter mPagerAdapter;
    public static MainScreenFragment[] viewFragments = new MainScreenFragment[9];
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.pager_fragment, container, false);
        mPagerHandler = (ViewPager) rootView.findViewById(R.id.pager);
        mPagerAdapter = new PageAdapter(getChildFragmentManager(), getActivity());
        for (int i = 0;i < NUM_PAGES;i++)
        {
            //Date fragmentdate = new Date(System.currentTimeMillis()+((i-2)*86400000));
            //SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
            viewFragments[i] = new MainScreenFragment();
            //viewFragments[i].setFragmentDate(mformat.format(fragmentdate));
            int number = i+1;
            viewFragments[i].setFragmentCategory(String.valueOf(number));
        }
        mPagerHandler.setAdapter(mPagerAdapter);
        mPagerHandler.setCurrentItem(MainActivity.current_fragment);
        return rootView;
    }

}
