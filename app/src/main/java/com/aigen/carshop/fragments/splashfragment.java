package com.aigen.carshop.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aigen.carshop.BaseFragment;
import com.aigen.carshop.R;
import com.aigen.carshop.activities.MainActivity;
import com.aigen.carshop.controller.MainBaseApplication;
import com.aigen.carshop.db.model.carmodel;
import com.aigen.carshop.utilities.appsharedprefsettings;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class splashfragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return getView() != null ? getView() : inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new SaveTask().execute(view);
    }

    private class SaveTask extends AsyncTask<View, Void, View> {

        @Override
        protected View doInBackground(View... vm) {

            String resultJsonDummy = "[\n" +
                    "  {\n" +
                    "    \"id\": 1,\n" +
                    "    \"car_name\": \"Maruti Suzuki Dzire Zxi+ AMT\",\n" +
                    "    \"car_type\": \"Sedan\",\n" +
                    "    \"manufactured_date\": \"2015\",\n" +
                    "    \"insurance\": \"till March'20\",\n" +
                    "    \"color\": \"Silky White\",\n" +
                    "    \"km_driven\": \"8000km\",\n" +
                    "    \"fuel_type\": \"Petrol\",\n" +
                    "    \"city_address\": \"Kandivali(w), Mumbai\",\n" +
                    "    \"added_on\": \"26-10-2019 00:00:00\",\n" +
                    "    \"price\": \"Rs.800000\\/-\",\n" +
                    "    \"image_array\": [\n" +
                    "      \"https://auto.ndtvimg.com/car-images/colors/maruti-suzuki/dzire/maruti-suzuki-dzire-pearl-arctic-white.png\"\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 2,\n" +
                    "    \"car_name\": \"Mahindra XUV 500 AT\",\n" +
                    "    \"car_type\": \"XUV\",\n" +
                    "    \"manufactured_date\": \"2014\",\n" +
                    "    \"insurance\": \"Expired\",\n" +
                    "    \"color\": \"Muddy brown\",\n" +
                    "    \"km_driven\": \"12000km\",\n" +
                    "    \"fuel_type\": \"Diesel\",\n" +
                    "    \"city_address\": \"Borivali(e), Mumbai\",\n" +
                    "    \"added_on\": \"27-10-2019 00:00:00\",\n" +
                    "    \"price\": \"Rs.1400000\\/-\",\n" +
                    "    \"image_array\": [\n" +
                    "      \"https://5.imimg.com/data5/GG/SF/GLADMIN-67875624/mahindra-xuv500-car-500x500.png\",\n" +
                    "      \"https://auto.ndtvimg.com/car-images/colors/mahindra/xuv500/mahindra-xuv500-crimson-red.png\"\n" +
                    "    ]\n" +
                    "  }\n" +
                    "]";
            try {
                JSONArray jsonArray = new JSONArray(resultJsonDummy);
                if (jsonArray != null && jsonArray.length() > 0) {
                    carmodel cs = new carmodel();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectField = jsonArray.optJSONObject(i);
                        if (jsonObjectField != null && jsonObjectField.has("id")) {
                            cs.setId(jsonObjectField.optInt("id"));
                            cs.setCar_name(jsonObjectField.optString("car_name"));
                            cs.setCar_type(jsonObjectField.optString("car_type"));
                            cs.setAdded_on(jsonObjectField.optString("added_on"));
                            cs.setCity_address(jsonObjectField.optString("city_address"));
                            cs.setColor(jsonObjectField.optString("color"));
                            cs.setFuel_type(jsonObjectField.optString("fuel_type"));
                            cs.setInsurance(jsonObjectField.optString("insurance"));
                            cs.setKm_driven(jsonObjectField.optString("km_driven"));
                            cs.setManufactured_date(jsonObjectField.optString("manufactured_date"));
                            cs.setPrice(jsonObjectField.optString("price"));
                            cs.setImage_array(jsonObjectField.optJSONArray("image_array").toString());
                            MainBaseApplication.getDBinstance().myDAO().addDummyData(cs);
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return vm[0];
        }

        @Override
        protected void onPostExecute(final View view) {
            super.onPostExecute(view);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (appsharedprefsettings.getIsLoggedin(mcontext)) {
                        ((MainActivity)mcontext).loadHomeScreen();
                    }else{
                        ((MainActivity)mcontext).loadLoginScreen();
                    }

                }
            }, 1500);
        }
    }


}
