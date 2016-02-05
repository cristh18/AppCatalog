package com.cristhian.appcatalog.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.adapters.AppAdapter;
import com.cristhian.appcatalog.interfaces.ItemClickListener;
import com.cristhian.appcatalog.models.AppImage;
import com.cristhian.appcatalog.models.ApplicationData;
import com.cristhian.appcatalog.repository.AppRepository;
import com.cristhian.appcatalog.repository.ImageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristhian on 2/4/2016.
 */
public class MainScreenFragment extends Fragment {

    private final String LOG_TAG = MainScreenFragment.class.getSimpleName();
    public static int count = 0;


    private String[] fragmentCategory = new String[1];
    private int last_selected_item = -1;

    private RecyclerView myRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    public static AppAdapter customListAdapter;

    List<ApplicationData> appsData;


    public MainScreenFragment() {
    }

    public void setFragmentCategory(String category) {
        fragmentCategory[0] = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        appsData = new ArrayList<>();
        for (int j = 0; j < fragmentCategory.length; j++) {
            Log.e(LOG_TAG, "Value fragmentDate: " + fragmentCategory[j]);
            int number = Integer.parseInt(fragmentCategory[j]) + 1;
            getAppInfo(getActivity(), String.valueOf(number));
        }

        customListAdapter = new AppAdapter(getActivity(), appsData);

        myRecyclerView = (RecyclerView) rootView.findViewById(R.id.app_list);
        myRecyclerView.setAdapter(customListAdapter);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        customListAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position, ApplicationData data) {
                ApplicationData applicationData = data;
                Log.e(LOG_TAG, "click!!! " + applicationData.getApplicationName() + " click!!!");
                showDetailInfoApp(data);
            }
        });

        return rootView;
    }

    private void getAppInfo(Context context, String categoryId) {
        ImageRepository imageRepository = ImageRepository.getImageRepoInstance();
        AppRepository appRepository = AppRepository.getAppRepoInstance();
        List<AppImage> imagesApp = imageRepository.getImagesByCategory(context, categoryId, "100");
        List<ApplicationData> apps = new ArrayList<>();
        for (AppImage image : imagesApp) {
            ApplicationData app = new ApplicationData();
            app = appRepository.getAppById(context, image.getApplicationIdentifier());
            app.setApplicationImage(image);
            apps.add(app);
        }

        appsData.addAll(apps);
    }

    private void showDetailInfoApp(ApplicationData applicationData) {
        FragmentManager fm = getFragmentManager();
        DetailAppFragment dialogFragment = new DetailAppFragment();
        dialogFragment.setApplicationData(applicationData);
        dialogFragment.show(fm, "Sample Fragment");
    }

}
