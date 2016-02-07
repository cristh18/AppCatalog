package com.cristhian.appcatalog.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.interfaces.ItemClickListener;
import com.cristhian.appcatalog.models.ApplicationData;
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

    private ItemClickListener mListener;

    public void setOnItemClickListener(ItemClickListener listener) {
        mListener = listener;
    }

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

        final ApplicationData appData = appsData.get(position);
        String appImageURL = appData.getApplicationImage().getImagerUrl();
        String appTitle = appData.getApplicationTitle();
        Context context = appsViewHolder.imageView.getContext();
        Picasso.with(context).load(appImageURL).placeholder(R.drawable.placeholder).error(R.drawable.placeholder_error).into(appsViewHolder.imageView);
        appsViewHolder.textView.setText(appTitle);

        appsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(v, position, appData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appsData.size();
    }

    private ApplicationData getItem(int position) {
        return appsData.get(position);
    }

    public void add(ApplicationData item) {
        appsData.add(item);
        this.notifyDataSetChanged();
    }

    public void clear() {
        int size = this.appsData.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.appsData.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }

}
