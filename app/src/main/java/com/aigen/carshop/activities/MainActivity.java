package com.aigen.carshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import android.os.Bundle;

import com.aigen.carshop.BaseActivity;
import com.aigen.carshop.R;

public class MainActivity extends BaseActivity {

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavController = new NavController(this);
    }
}
