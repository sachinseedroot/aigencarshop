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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

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

    public void loadFragments(View view, Bundle arg, int setDestinationNavigate, int setDestinationPopUpTo, boolean isBack) {
        Navigation.findNavController(view).navigate(setDestinationNavigate, arg, new NavOptions.Builder()
                .setEnterAnim(R.anim.fragment_slide_right_enter)
                .setPopEnterAnim(R.anim.fragment_slide_left_enter)
                .setExitAnim(R.anim.fragment_slide_left_exit)
                .setPopExitAnim(R.anim.fragment_slide_right_exit)
                .setPopUpTo(R.id.nav_host_fragment, isBack)
                .build());
    }

    public void loadFragmentsNew(Fragment fragment, Bundle bundle, int container, Boolean addToBackStack, String addReplace) {
        addReplaceFrag(fragment, bundle, container, addToBackStack, addReplace);
    }

    public void addReplaceFrag(Fragment fragment, Bundle bundle, int container, boolean addToBackStack, String addReplace) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_top);
        if (addToBackStack)
            fragmentTransaction.addToBackStack("");
        if (bundle != null)
            fragment.setArguments(bundle);
        if (addReplace.equalsIgnoreCase("add")) {
            fragmentTransaction.add(container, fragment).commit();
        } else {
            fragmentTransaction.replace(container, fragment).commit();
        }
    }

    public void showSuccessSnackbar(String message, View view) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(Color.parseColor(SNACK_SUCCESS_COLOR));
        snackbar.show();
    }

    public void showErrorSnackbar(String message, View view) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(Color.parseColor(SNACK_ERROR_COLOR));
        snackbar.show();
    }

    public void loadImage(Context context, String url, ImageView view) {
        Glide.with(context).load(url).into(view);
    }

    public void loadImageDrawable(Context context, int drawable, ImageView view) {
        Glide.with(context).load(drawable).into(view);
    }

    public void showDialog(Context context,String msg) {
            dialog = ProgressDialog.show(context, "",
                    msg, true);
    }

    public void hideDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
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
