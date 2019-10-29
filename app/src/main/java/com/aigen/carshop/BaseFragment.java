package com.aigen.carshop;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public Context mcontext;
    private BaseActivity activity;


    @Override
    public void onAttach(Context context) {
        this.mcontext = context;
        super.onAttach(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }


    public void loadFragment(View view, Bundle arg, int setDestinationNavigate, int setDestinationPopUpTo, boolean isBack) {
        if (activity != null)
            activity.loadFragments(view, arg, setDestinationNavigate, setDestinationPopUpTo, isBack);
    }

    public Activity getBaseActivity() {
        activity = (BaseActivity) getActivity();
        return activity;
    }


    public void loadFragmentsNew(Fragment fragment, Bundle bundle, int container, Boolean addToBackStack, String addReplace) {
        if (activity != null)
            activity.loadFragmentsNew(fragment, bundle, container, addToBackStack, addReplace);
    }

    public void showErrorSnackbar(String message, View view) {
        activity.showErrorSnackbar(message, view);
    }

    public void showSuccessSnackbar(String message, View view) {
        activity.showSuccessSnackbar(message, view);
    }

    public void loadImage(Context context, String url, ImageView view) {
        activity.loadImage(context, url, view);
    }

    public void loadImageDrawable(Context context, int drawable, ImageView view) {
        activity.loadImageDrawable(context, drawable, view);
    }

    public void showDialog(String msg) {
        activity.showDialog(mcontext,msg);
    }

    public void hideDialog() {
        activity.hideDialog();
    }

}
