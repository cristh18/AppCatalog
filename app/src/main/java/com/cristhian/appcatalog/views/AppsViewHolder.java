package com.cristhian.appcatalog.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cristhian.appcatalog.R;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class AppsViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout placeHolder;

    public LinearLayout placeNameHolder;

    public ImageView imageView;

    public TextView textView;

    public AppsViewHolder(View v) {
        super(v);
        placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
        placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
        imageView = (ImageView) v.findViewById(R.id.image);
        textView = (TextView) itemView.findViewById(R.id.placeName);
    }
}
