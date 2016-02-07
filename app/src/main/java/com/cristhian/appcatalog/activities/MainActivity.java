package com.cristhian.appcatalog.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.cristhian.appcatalog.R;
import com.cristhian.appcatalog.fragments.PagerFragment;
import com.cristhian.appcatalog.interfaces.ICatalogResponse;
import com.cristhian.appcatalog.service.AppProvider;
import com.cristhian.appcatalog.network.CatalogTask;
import com.cristhian.appcatalog.utils.Utilies;

/**
 * Created by ctolosa on 02/02/2016.
 */
public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private AlertDialog.Builder dialog;
    private PagerFragment pagerFragment;
    public static int current_fragment = 2;
    private final String save_tag = "Save Test";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToMainScreen();

        if (LaunchScreenActivity.IS_TABLET) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        showExitDialog();
    }

    /**
     *
     */
    private void goToMainScreen() {
        pagerFragment = new PagerFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_main, pagerFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     */
    private void showExitDialog() {
        dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getResources().getString(R.string.app_name));
        dialog.setMessage(getResources().getString(R.string.exit_app_msg));
        dialog.setCancelable(false);
        dialog.setPositiveButton(getResources().getString(R.string.yes_option), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                finish();
            }
        });
        dialog.setNegativeButton(getResources().getString(R.string.no_option), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.dismiss();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        Log.v(save_tag, "will save");
        Log.v(save_tag, "fragment: " + String.valueOf(pagerFragment.mPagerHandler.getCurrentItem()));
        outState.putInt("Pager_Current", pagerFragment.mPagerHandler.getCurrentItem());
        getSupportFragmentManager().putFragment(outState, "pagerFragment", pagerFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        Log.e(save_tag,"will retrive");
        Log.e(save_tag,"fragment: "+String.valueOf(savedInstanceState.getInt("Pager_Current")));
        current_fragment = savedInstanceState.getInt("Pager_Current");
        pagerFragment = (PagerFragment) getSupportFragmentManager().getFragment(savedInstanceState,"pagerFragment");
        super.onRestoreInstanceState(savedInstanceState);
    }


}