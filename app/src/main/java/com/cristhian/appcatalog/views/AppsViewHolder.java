package com.cristhian.appcatalog.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristhian.appcatalog.R;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class AppsViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public TextView textView;

    public AppsViewHolder(View v) {
        super(v);
        imageView = (ImageView) v.findViewById(R.id.image);
        textView = (TextView)v.findViewById(R.id.label_title);
    }
}
