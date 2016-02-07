package com.cristhian.appcatalog.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.activities.LaunchScreenActivity;
import com.cristhian.appcatalog.activities.MainActivity;
import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.adapters.PageAdapter;
import com.cristhian.appcatalog.repository.CategoryRepository;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class PagerFragment extends Fragment {

    CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();

    public static int NUM_PAGES;
    public ViewPager mPagerHandler;
    private PageAdapter mPagerAdapter;
    public static MainScreenFragment[] viewFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numberCategories = categoryRepository.getCategories(getActivity()).size();
        if (LaunchScreenActivity.IS_TABLET) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        NUM_PAGES = numberCategories;
        viewFragments = new MainScreenFragment[numberCategories];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pager_fragment, container, false);

        mPagerHandler = (ViewPager) rootView.findViewById(R.id.pager);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) rootView.findViewById(R.id.pager_header);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.blue09));
        pagerTabStrip.setTextColor(getResources().getColor(R.color.white01));

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
