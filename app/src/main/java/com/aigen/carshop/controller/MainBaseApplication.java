package com.aigen.carshop.controller;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;


import com.aigen.carshop.db.AppDataBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import io.reactivex.schedulers.Schedulers;

public class MainBaseApplication extends Application {

    private static boolean activityVisible;
    private static AppDataBase appDataBase;
    private static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;

    }


    public static synchronized AppDataBase getDBinstance() {

        try {
            if (appDataBase == null) {
                appDataBase = Room.databaseBuilder(mcontext, AppDataBase.class, "carshop").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appDataBase;
    }

    public static void destroyInstance() {
        if (appDataBase != null && appDataBase.isOpen()) {
            appDataBase.close();
            appDataBase = null;
        }
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

}
