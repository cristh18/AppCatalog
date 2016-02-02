package com.cristhian.appcatalog;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cristhian.appcatalog.fragments.HomeFragment;
import com.cristhian.appcatalog.fragments.PagerFragment;
import com.cristhian.appcatalog.network.CatalogTask;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialog;
    private PagerFragment my_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.content_main, new HomeFragment());
//        fragmentTransaction.commit();

        my_main = new PagerFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_main, my_main)
                .commit();

        showExitDialog();
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

}
