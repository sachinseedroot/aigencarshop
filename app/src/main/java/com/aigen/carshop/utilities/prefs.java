package com.aigen.carshop.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public final class prefs {

    public static SharedPreferences get(Context context){
        return context.getSharedPreferences("carshop" , Context.MODE_PRIVATE);
    }
}