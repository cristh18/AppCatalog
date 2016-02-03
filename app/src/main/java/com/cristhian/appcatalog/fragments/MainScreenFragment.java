package com.cristhian.appcatalog.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.adapters.AppAdapter;
import com.cristhian.appcatalog.entities.AppEntity;
import com.cristhian.appcatalog.managers.DatabaseContract;
import com.cristhian.appcatalog.models.Entry;
import com.cristhian.appcatalog.network.CatalogTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainScreenFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public static AppAdapter appAdapter;
    public static final int APPS_LOADER = 0;
    public static int count = 0;


    private String[] fragmentdate = new String[1];
    private int last_selected_item = -1;

    public MainScreenFragment() {
    }



//    public void setFragmentDate(String date) {
//        fragmentdate[0] = date;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        final RecyclerView app_list = (RecyclerView) rootView.findViewById(R.id.app_list);
        appAdapter = new AppAdapter(getActivity(), null);
        app_list.setAdapter(appAdapter);
        getLoaderManager().initLoader(APPS_LOADER, null, this);


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
        return new CursorLoader(getActivity(), AppEntity.buildAppWithId(),
                null, null, fragmentdate, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        int i = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            i++;
            cursor.moveToNext();
        }
        appAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        appAdapter.swapCursor(null);
    }

}
