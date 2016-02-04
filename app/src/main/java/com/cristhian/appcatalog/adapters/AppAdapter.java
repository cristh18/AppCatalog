package com.cristhian.appcatalog.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.models.AppImage;
import com.cristhian.appcatalog.models.ApplicationData;
import com.cristhian.appcatalog.models.ImImage;
import com.cristhian.appcatalog.utils.RecyclerViewCursorAdapter;
import com.cristhian.appcatalog.views.AppsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class AppAdapter extends RecyclerView.Adapter<AppsViewHolder> {

    private final String LOG_TAG = AppAdapter.class.getSimpleName();
    private Context mContext;
    private final List<ApplicationData> appsData;



    public AppAdapter(Context context, List<ApplicationData> appsData) {
        this.appsData = appsData;
        this.mContext = context;
    }

    @Override
    public AppsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.apps_list_item, viewGroup, false);

        return new AppsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AppsViewHolder appsViewHolder, final int position) {
        Log.e(LOG_TAG, "ENTER TO BINDVIEWHOLDER");

        ApplicationData appData = appsData.get(position);
        String appImageURL = appData.getApplicationImage().getImagerUrl();
        String appTitle = appData.getApplicationTitle();
        Context context = appsViewHolder.imageView.getContext();
        Picasso.with(context).load(appImageURL).noFade().into(appsViewHolder.imageView);
        appsViewHolder.textView.setText(appTitle);

    }

    @Override
    public int getItemCount() {
        return appsData.size();
    }

    public void add(ApplicationData item) {
        appsData.add(item);
        this.notifyDataSetChanged();
    }

}
