package com.cristhian.appcatalog.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cristhian.appcatalog.R;

/**
 * Created by Cristhian on 2/4/2016.
 */
public class DetailAppFragment extends DialogFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_app_detail, container, false);
        getDialog().setTitle("Simple Dialog");

        Button dismiss = (Button) rootView.findViewById(R.id.dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }

}
