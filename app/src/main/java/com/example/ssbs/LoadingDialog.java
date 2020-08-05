package com.example.ssbs;/*
 * Created by VIMAL on 01-08-2020
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

public class LoadingDialog extends Activity {
    private Activity activity;
    private AlertDialog alertDialog;

    public LoadingDialog(Activity myactivity) {
        this.activity = myactivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_progressbar, null));
        builder.setCancelable(true);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dismissDialog() {
        alertDialog.dismiss();
    }
}
