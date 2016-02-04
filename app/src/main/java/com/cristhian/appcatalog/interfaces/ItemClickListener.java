package com.cristhian.appcatalog.interfaces;

import android.view.View;

import com.cristhian.appcatalog.models.ApplicationData;

/**
 * Created by Cristhian on 11/8/2015.
 */
public interface ItemClickListener {

    public void onItemClicked(View view, int position, ApplicationData data);
}
