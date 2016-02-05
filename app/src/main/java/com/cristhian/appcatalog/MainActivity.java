package com.cristhian.appcatalog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cristhian.appcatalog.fragments.PagerFragment;
import com.cristhian.appcatalog.interfaces.ICatalogResponse;
import com.cristhian.appcatalog.service.AppProvider;
import com.cristhian.appcatalog.network.CatalogTask;
import com.cristhian.appcatalog.utils.Utilies;

public class MainActivity extends AppCompatActivity implements ICatalogResponse {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private AlertDialog.Builder dialog;
    private PagerFragment pagerFragment;
    public static int current_fragment = 2;
    private final String save_tag = "Save Test";

    AppProvider appProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appProvider = new AppProvider();


        if (savedInstanceState == null) {
            if (Utilies.validateCatalog(this)){
                Log.e(LOG_TAG, "Catalog exist!!!!!!!");
                goToMainScreen();
            }else {
                String url = getString(R.string.base_url);
                getCatalog(url);
            }
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

    /**
     * @param url
     */
    private void getCatalog(String url) {
        CatalogTask catalogTask = new CatalogTask(this, this);
        catalogTask.execute(url);
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
        Log.v(save_tag,"will save");
        Log.v(save_tag, "fragment: " + String.valueOf(pagerFragment.mPagerHandler.getCurrentItem()));
        outState.putInt("Pager_Current",pagerFragment.mPagerHandler.getCurrentItem());
        getSupportFragmentManager().putFragment(outState,"pagerFragment",pagerFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        Log.v(save_tag,"will retrive");
        Log.v(save_tag,"fragment: "+String.valueOf(savedInstanceState.getInt("Pager_Current")));
        current_fragment = savedInstanceState.getInt("Pager_Current");
        pagerFragment = (PagerFragment) getSupportFragmentManager().getFragment(savedInstanceState,"my_main");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void responseCatalog(Boolean response) {
        if (response) {
            goToMainScreen();
            Toast.makeText(this, "Everything is OK", Toast.LENGTH_LONG);
        } else {
            Toast.makeText(this, "Something is wrong", Toast.LENGTH_LONG);
        }
    }
}
