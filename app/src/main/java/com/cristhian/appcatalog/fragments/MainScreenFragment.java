package com.cristhian.appcatalog.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.adapters.AppAdapter;
import com.cristhian.appcatalog.entities.AppEntity;
import com.cristhian.appcatalog.entities.ImageEntity;
import com.cristhian.appcatalog.managers.DatabaseContract;
import com.cristhian.appcatalog.models.Entry;
import com.cristhian.appcatalog.network.CatalogTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainScreenFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int APPS_LOADER = 1;
    public static int count = 0;


    private String[] fragmentCategory = new String[1];
    private int last_selected_item = -1;

    private RecyclerView myRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    public static AppAdapter customListAdapter;

    public MainScreenFragment() {
    }

    public void setFragmentCategory(String category) {
        fragmentCategory[0] = category;
    }

    //    public void setFragmentDate(String date) {
//        fragmentdate[0] = date;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        customListAdapter = new AppAdapter(getActivity());
        myRecyclerView = (RecyclerView) rootView.findViewById(R.id.app_list);
        myRecyclerView.setAdapter(customListAdapter);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getLoaderManager().restartLoader(APPS_LOADER, null, this);


//        getLoaderManager().initLoader(SCORES_LOADER, null, this);
//        mAdapter.detail_match_id = MainActivity.selected_match_id;
//        score_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ViewHolder selected = (ViewHolder) view.getTag();
//                mAdapter.detail_match_id = selected.match_id;
//                MainActivity.selected_match_id = (int) selected.match_id;
//                mAdapter.notifyDataSetChanged();
//            }
//        });
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        for (int j = 0; j< fragmentCategory.length; j++){
            Log.e(this.getClass().getName(), "Value fragmentDate: " + fragmentCategory[j]);
        }
        Log.e(this.getClass().getName(), "========================");
        return new CursorLoader(getActivity(), ImageEntity.buildImageUri(id),
                null, null, fragmentCategory, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//
//        int i = 0;
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            i++;
//            cursor.moveToNext();
//        }
//        customListAdapter.swapCursor(cursor);


        switch (loader.getId()) {
            case APPS_LOADER:

                this.customListAdapter.swapCursor(cursor);
                break;
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //customListAdapter.swapCursor(null);
        switch (loader.getId()) {
            case APPS_LOADER:
                this.customListAdapter.swapCursor(null);
                break;
        }
    }

}
