package com.cristhian.appcatalog.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.entities.ImageEntity;
import com.cristhian.appcatalog.interfaces.OnItemClick;
import com.cristhian.appcatalog.models.Entry;
import com.cristhian.appcatalog.models.ImImage;
import com.cristhian.appcatalog.utils.CursorRecyclerViewAdapter;
import com.cristhian.appcatalog.utils.RecyclerViewCursorAdapter;
import com.cristhian.appcatalog.views.AppsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class AppAdapter extends RecyclerViewCursorAdapter<AppsViewHolder> {

    private final String LOG_TAG = AppAdapter.class.getSimpleName();
    private final LayoutInflater layoutInflater;

    public AppAdapter(Context context) {
        super();
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public AppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //final View view = this.layoutInflater.inflate(R.layout.apps_list_item, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apps_list_item, parent, false);
        return new AppsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppsViewHolder holder, Cursor cursor) {
//        String itemImagew = cursor.getString(cursor.getColumnIndex(ImageEntity.LABEL));
//        Log.e(LOG_TAG, "DATA CONSULTED!!!: " + itemImagew);
//        Context context = holder.imageView.getContext();
//        Picasso.with(context).load(itemImagew).noFade().into(holder.imageView);
    }
}
