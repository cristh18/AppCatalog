package com.cristhian.appcatalog.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cristhian.appcatalog.fragments.PagerFragment;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    public static final int NUM_PAGES = 9;
    Context context;

    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return PagerFragment.viewFragments[i];
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
            return "Photo & video";
        } else if (category == 3) {
            return "Health & Fitness";
        } else if (category == 4) {
            return "Music";
        } else if (category == 5) {
            return "Education";
        } else if (category == 6) {
            return "Finance";
        } else if (category == 7) {
            return "Shopping";
        } else if (category == 8) {
            return "Navigation";
        } else {
            return "";
        }
    }
}
