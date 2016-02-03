package com.cristhian.appcatalog.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.interfaces.OnItemClick;
import com.cristhian.appcatalog.models.Entry;
import com.cristhian.appcatalog.models.ImImage;
import com.cristhian.appcatalog.utils.CursorRecyclerViewAdapter;
import com.cristhian.appcatalog.views.AppsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class AppAdapter extends CursorRecyclerViewAdapter<AppsViewHolder> {

    Context context;

    public AppAdapter(Context context,Cursor cursor){
        super(context,cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(AppsViewHolder viewHolder, Cursor cursor) {
        //ImImage image = MyListItem.fromCursor(cursor); -- get image from cursor
        String itemImage = "";
        Picasso.with(context).load(itemImage).noFade().into(viewHolder.imageView);
    }

    @Override
    public AppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.apps_list_item, parent, false);
        AppsViewHolder vh = new AppsViewHolder(itemView);
        return vh;
    }
}
