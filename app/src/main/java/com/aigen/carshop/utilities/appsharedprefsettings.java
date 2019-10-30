package com.aigen.carshop.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class appsharedprefsettings {

    public static void setIsLoggedin(Context context, boolean IsLoggedin) {
        SharedPreferences prefss = prefs.get(context);
        SharedPreferences.Editor editor = prefss.edit();
        editor.putBoolean("IsLoggedin", IsLoggedin);
        editor.commit();
    }

    public static boolean getIsLoggedin(Context context) {
        SharedPreferences prefss = prefs.get(context);
        return prefss.getBoolean("IsLoggedin", false);
    }

}
