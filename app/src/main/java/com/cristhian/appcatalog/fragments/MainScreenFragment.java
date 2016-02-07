package com.cristhian.appcatalog.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.activities.LaunchScreenActivity;
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

    private String[] fragmentCategory = new String[1];

    private RecyclerView myRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    public static AppAdapter customListAdapter;

    List<ApplicationData> appsData;

    public MainScreenFragment() {
    }

    public void setFragmentCategory(String category) {
        fragmentCategory[0] = category;
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d(LOG_TAG, "savedInstanceState: " + savedInstanceState);
        Log.d(LOG_TAG, "fragmentCategory: " + fragmentCategory);
        Log.d(LOG_TAG, "fragmentCategory first element: " + fragmentCategory[0]);

        appsData = new ArrayList<>();
        Log.d(LOG_TAG, "Value appsData: " + appsData);

        if (fragmentCategory[0] == null) {
            if (savedInstanceState != null) {
                customListAdapter.clear();
                setFragmentCategory(String.valueOf(savedInstanceState.getInt("Pager_Current")));
            }
        }

        Log.d(LOG_TAG, "Value fragmentCategory: " + fragmentCategory);
        for (int j = 0; j < fragmentCategory.length; j++) {
            Log.d(LOG_TAG, "Value fragmentCategory j element: " + fragmentCategory[j]);
            int number = Integer.parseInt(fragmentCategory[j]) + 1;
            getAppInfo(getActivity(), String.valueOf(number));
        }

        if (LaunchScreenActivity.IS_TABLET) {
            mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        } else {
            mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        }

        myRecyclerView = (RecyclerView) rootView.findViewById(R.id.app_list);
        myRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        myRecyclerView.setHasFixedSize(true);

        customListAdapter = new AppAdapter(getActivity(), appsData);
        myRecyclerView.setAdapter(customListAdapter);

        customListAdapter.setOnItemClickListener(onItemClickListener);

        return rootView;
    }

    /**
     *
     */
    ItemClickListener onItemClickListener = new ItemClickListener() {
        @Override
        public void onItemClicked(View view, int position, ApplicationData data) {
            ApplicationData applicationData = data;
            Log.d(LOG_TAG, "click!!! " + applicationData.getApplicationName() + " click!!!");
            showDetailInfoApp(data);
        }
    };

    /**
     * @param context
     * @param categoryId
     */
    private void getAppInfo(Context context, String categoryId) {
        ImageRepository imageRepository = ImageRepository.getImageRepoInstance();
        AppRepository appRepository = AppRepository.getAppRepoInstance();
        List<AppImage> imagesApp = imageRepository.getImagesByCategory(context, categoryId, context.getResources().getString(R.string.imageSize));
        List<ApplicationData> apps = new ArrayList<>();
        for (AppImage image : imagesApp) {
            ApplicationData app = new ApplicationData();
            app = appRepository.getAppById(context, image.getApplicationIdentifier());
            app.setApplicationImage(image);
            apps.add(app);
        }

        appsData.addAll(apps);
    }

    /**
     * @param applicationData
     */
    private void showDetailInfoApp(ApplicationData applicationData) {
        FragmentManager fm = getFragmentManager();
        DetailAppFragment dialogFragment = new DetailAppFragment();
        dialogFragment.setApplicationData(applicationData);
        dialogFragment.show(fm, "App Detail");
    }
}
