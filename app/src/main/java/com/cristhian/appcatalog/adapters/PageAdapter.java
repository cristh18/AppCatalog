package com.cristhian.appcatalog.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.text.format.Time;


import com.cristhian.appcatalog.fragments.MainScreenFragment;
import com.cristhian.appcatalog.fragments.PagerFragment;

import java.text.SimpleDateFormat;


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
//        return getDayName(context, System.currentTimeMillis() + ((position - 2) * 86400000));
        return getPageTitle(context, position);
    }

    public String getDayName(Context context, long dateInMillis) {
        // If the date is today, return the localized version of "Today" instead of the actual
        // day name.

        Time t = new Time();
        t.setToNow();
        int julianDay = Time.getJulianDay(dateInMillis, t.gmtoff);
        int currentJulianDay = Time.getJulianDay(System.currentTimeMillis(), t.gmtoff);
        if (julianDay == currentJulianDay) {
            return "Today";
        } else if (julianDay == currentJulianDay + 1) {
            return "Tomorrow";
        } else if (julianDay == currentJulianDay - 1) {
            return "Yesterday";
        } else {
            Time time = new Time();
            time.setToNow();
            // Otherwise, the format is just the day of the week (e.g "Wednesday".
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            return dayFormat.format(dateInMillis);
        }
    }

    private String getPageTitle(Context context, int category) {
        String title = "";

        switch (category) {
            case 1:
                title = "Games";
                break;
            case 2:
                title = "Social Networking";
                break;
            case 3:
                title = "Health & Fitness";
                break;
            case 4:
                title = "Photo & video";
                break;
            case 5:
                title = "Music";
                break;
            case 6:
                title = "Education";
                break;
            case 7:
                title = "Finance";
                break;
            case 8:
                title = "Entertainment";
                break;
            case 9:
                title = "Shopping";
                break;
        }

        return title;
    }
}
