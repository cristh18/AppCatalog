package com.cristhian.appcatalog.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.models.ApplicationData;
import com.cristhian.appcatalog.utils.Utilies;
import com.squareup.picasso.Picasso;

/**
 * Created by Cristhian on 2/4/2016.
 */
public class DetailAppFragment extends DialogFragment {

    ImageView imageViewApp;
    TextView textViewTitle;
    TextView textViewSummary;
    private ApplicationData applicationData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_app_detail, container, false);
        getDialog().setTitle(Utilies.getShortAppName(applicationData.getApplicationTitle()));

        imageViewApp = (ImageView) rootView.findViewById(R.id.appImageDetail);
        textViewTitle = (TextView) rootView.findViewById(R.id.appTitle);
        textViewSummary = (TextView) rootView.findViewById(R.id.appSummary);
        Picasso.with(getActivity()).load(applicationData.getApplicationImage().getImagerUrl()).placeholder(R.drawable.placeholder).error(R.drawable.placeholder_error).into(imageViewApp);
        textViewTitle.setText(applicationData.getApplicationTitle());
        textViewSummary.setText(applicationData.getApplicationSummary());

        Button dismiss = (Button) rootView.findViewById(R.id.dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }

    public ApplicationData getApplicationData() {
        return applicationData;
    }

    public void setApplicationData(ApplicationData applicationData) {
        this.applicationData = applicationData;
    }
}
