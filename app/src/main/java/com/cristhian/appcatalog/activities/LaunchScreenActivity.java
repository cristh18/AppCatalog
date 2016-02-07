package com.cristhian.appcatalog.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.interfaces.ICatalogResponse;
import com.cristhian.appcatalog.network.CatalogTask;
import com.cristhian.appcatalog.service.AppProvider;
import com.cristhian.appcatalog.utils.Utilies;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class LaunchScreenActivity extends AppCompatActivity implements ICatalogResponse {


    private final String LOG_TAG = MainActivity.class.getSimpleName();

    Intent intent;

    AppProvider appProvider;

    boolean executeService;
    boolean existCatalog;

    public static boolean IS_TABLET = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IS_TABLET = Utilies.isTablet(this);
        appProvider = new AppProvider();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        if (IS_TABLET) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        setContentView(R.layout.activity_launch_screen);
        String url = getString(R.string.base_url);
        executeService = false;
        existCatalog = false;

        if (savedInstanceState == null) {
            if (Utilies.validateCatalog(this)) {
                Log.e(LOG_TAG, "Catalog exist!!!!!!!");
                executeService = false;
                existCatalog = true;
                getCatalog(url, executeService, existCatalog);
            } else {
                executeService = true;
                existCatalog = false;
                getCatalog(url, executeService, existCatalog);
            }
        }
    }

    /**
     * @param url
     */
    private void getCatalog(String url, boolean executeService, boolean existCatalog) {
        Log.e(LOG_TAG, "Call catalogTask!!!!!!!");
        CatalogTask catalogTask = new CatalogTask(this, this);
        catalogTask.execute(url, executeService, existCatalog);
    }


    @Override
    public void responseCatalog(Boolean response) {
        if (response) {
            goToMainActivity();
            Toast.makeText(this, "Everything is OK", Toast.LENGTH_LONG);
        } else {
            Toast.makeText(this, "Something is wrong", Toast.LENGTH_LONG);
        }
    }

    /**
     *
     */
    private void goToMainActivity() {
        intent = new Intent(LaunchScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
