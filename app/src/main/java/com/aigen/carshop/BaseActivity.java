package com.aigen.carshop;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {

    public String SNACK_ERROR_COLOR = "#dd5a5a";
    public String SNACK_SUCCESS_COLOR = "#87cc6c";
    public static boolean isInternet;
    private MobileDataStateChangedReceiver receiver;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    public void loadImage(Context context, String url, ImageView view) {
        Glide.with(context).load(url).into(view);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (receiver == null)
            receiver = new MobileDataStateChangedReceiver();
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public class MobileDataStateChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                NetworkUtil networkUtil = new NetworkUtil();
                int state = networkUtil.getConnectivityStatus(context);
                if (state == networkUtil.TYPE_WIFI || state == networkUtil.TYPE_MOBILE) {
                    isInternet = true;
                } else if (state == networkUtil.TYPE_NOT_CONNECTED) {
                    isInternet = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class NetworkUtil {

        static final int TYPE_WIFI = 1;
        static final int TYPE_MOBILE = 2;
        static final int TYPE_NOT_CONNECTED = 0;

        public NetworkUtil() {

        }

        public int getConnectivityStatus(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (null != activeNetwork) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                    return TYPE_WIFI;

                if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                    return TYPE_MOBILE;
            }
            return TYPE_NOT_CONNECTED;
        }
    }
}
