package com.cristhian.appcatalog.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.network.CatalogTask;

/**
 * Created by Cristhian on 2/1/2016.
 */
public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        String url = getString(R.string.base_url);
        getAppsCatalog(url);
        return view;
    }



    /**
     *
     */
    private void getAppsCatalog(String url){
        CatalogTask catalogTask = new CatalogTask();
        catalogTask.execute(url);
    }
}
