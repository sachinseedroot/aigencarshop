package com.aigen.carshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aigen.carshop.BaseActivity;
import com.aigen.carshop.R;
import com.aigen.carshop.fragments.homefragment;
import com.aigen.carshop.fragments.loginfragment;
import com.aigen.carshop.fragments.postadfragment;
import com.aigen.carshop.fragments.singleadfragment;
import com.aigen.carshop.fragments.splashfragment;

import java.util.Stack;
import java.util.TreeMap;

public class MainActivity extends BaseActivity {

    private splashfragment splashFragment;
    private Stack<Fragment> fragmentStack = new Stack<Fragment>();
    ;
    private FrameLayout frameLayoutContainer;
    private loginfragment loginf;
    private postadfragment postadf;
    private singleadfragment singlef;
    private homefragment homef;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayoutContainer = (FrameLayout) findViewById(R.id.container);
        loadSplashScreen();
    }

    @Override
    public void onBackPressed() {
        if (fragmentStack.size() > 1) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = fragmentStack.pop();
            ft.setCustomAnimations(R.anim.hold, R.anim.exit_to_right);
            Fragment lastFragment = fragmentStack.lastElement();
            lastFragment.onPause();
            ft.remove(fragment);
            lastFragment.onResume();
            ft.show(lastFragment);
            ft.commit();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                finishAffinity();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        }
    }

    public void loadSplashScreen() {
        splashFragment = new splashfragment();
        if (splashFragment.isAdded()) {
            return;
        }
        fragmentStack.clear();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(frameLayoutContainer.getId(), splashFragment);
        if (fragmentStack.size() > 0) {
            fragmentStack.lastElement().onPause();
            ft.hide(fragmentStack.lastElement());
        }
        fragmentStack.push(splashFragment);
        ft.commitAllowingStateLoss();
    }

    public void loadLoginScreen() {

        loginf = new loginfragment();

        if (loginf.isAdded()) {
            return;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.hold);
        ft.add(frameLayoutContainer.getId(), loginf);
        if (fragmentStack.size() > 0) {
            fragmentStack.lastElement().onPause();
            ft.hide(fragmentStack.lastElement());
        }
        fragmentStack.push(loginf);
        ft.commitAllowingStateLoss();
    }

    public void loadHomeScreen() {
        homef = new homefragment();
        if (homef.isAdded()) {
            return;
        }

        fragmentStack.clear();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.hold);
        ft.add(frameLayoutContainer.getId(), homef);
        if (fragmentStack.size() > 0) {
            fragmentStack.lastElement().onPause();
            ft.hide(fragmentStack.lastElement());
        }
        fragmentStack.push(homef);
        ft.commitAllowingStateLoss();
    }

    public void loadPostAdScreen() {
        postadf = new postadfragment();
        if (postadf.isAdded()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.hold);
        ft.add(frameLayoutContainer.getId(), postadf);
        if (fragmentStack.size() > 0) {
            fragmentStack.lastElement().onPause();
            ft.hide(fragmentStack.lastElement());
        }
        fragmentStack.push(postadf);
        ft.commitAllowingStateLoss();
    }

    public void loadSingleAdScreen(Bundle bl) {
        singlef = singleadfragment.newInstance(bl);

        if (singlef.isAdded()) {
            return;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.hold);
        ft.add(frameLayoutContainer.getId(), singlef);
        if (fragmentStack.size() > 0) {
            fragmentStack.lastElement().onPause();
            ft.hide(fragmentStack.lastElement());
        }
        fragmentStack.push(singlef);
        ft.commitAllowingStateLoss();
    }


}
