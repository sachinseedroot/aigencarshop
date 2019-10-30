package com.aigen.carshop.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.aigen.carshop.BaseActivity;
import com.aigen.carshop.BaseFragment;
import com.aigen.carshop.R;
import com.aigen.carshop.activities.MainActivity;
import com.aigen.carshop.controller.MainBaseApplication;
import com.aigen.carshop.db.model.carmodel;
import com.fxn.pix.Pix;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

public class postadfragment extends BaseFragment implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE = 101;
    private static final int SELECT_FILE = 100;
    private EditText input_name;
    private EditText input_type;
    private EditText input_md;
    private EditText input_ins;
    private EditText input_color;
    private EditText input_km;
    private EditText input_ft;
    private EditText input_ca;
    private EditText input_price;
    private TextView attachtv;
    private Button btnsave;
    private JSONArray imagearray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return getView() != null ? getView() : inflater.inflate(R.layout.fragment_post_ad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    public void initView(View view) {
        input_name = (EditText) view.findViewById(R.id.input_name);
        input_type = (EditText) view.findViewById(R.id.input_type);
        input_md = (EditText) view.findViewById(R.id.input_md);
        input_ins = (EditText) view.findViewById(R.id.input_ins);
        input_color = (EditText) view.findViewById(R.id.input_color);
        input_km = (EditText) view.findViewById(R.id.input_km);
        input_ft = (EditText) view.findViewById(R.id.input_ft);
        input_ca = (EditText) view.findViewById(R.id.input_ca);
        input_price = (EditText) view.findViewById(R.id.input_price);
        btnsave = (Button) view.findViewById(R.id.save_btn);
        attachtv = (TextView) view.findViewById(R.id.attachtv);
        btnsave.setOnClickListener(this);
        attachtv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        View views = getActivity().getCurrentFocus();
        switch (v.getId()) {

            case R.id.attachtv:
                if (views != null) {
                    InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(views.getWindowToken(), 0);
                }

                if (!hasPermissions(mcontext)) {
                    requestPermission();
                    return;
                }
                if (attachtv.getText().toString().trim().toLowerCase().contains("remove")) {
                    imagearray = new JSONArray();
                    attachtv.setText("Click to Attach Car Images +");
                    attachtv.setTextColor(ContextCompat.getColor(mcontext, R.color.colorPrimaryDark));
                } else {

                    Pix.start(postadfragment.this,                    //Activity or Fragment Instance
                            SELECT_FILE,                //Request code for activity results
                            5);
                }
                break;
            case R.id.save_btn:
                if (views != null) {
                    InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(views.getWindowToken(), 0);
                }

                if (checkModel() == true) {
                    new SavePost().execute();
                }
                break;

        }
    }

    public class SavePost extends AsyncTask<Void, Void, carmodel> {
        carmodel cs;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cs = new carmodel();
            cs.setCar_name(input_name.getText().toString().trim());
            cs.setCar_type(input_type.getText().toString().trim());
            cs.setManufactured_date(input_md.getText().toString().trim());
            cs.setInsurance(input_ins.getText().toString().trim());
            cs.setColor(input_color.getText().toString().trim());
            cs.setKm_driven(input_km.getText().toString().trim());
            cs.setFuel_type(input_ft.getText().toString().trim());
            cs.setCity_address(input_ca.getText().toString().trim());
            cs.setPrice(input_price.getText().toString().trim());
        }

        @Override
        protected carmodel doInBackground(Void... voids) {
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            String formattedDate = df.format(c);
            cs.setAdded_on(formattedDate);
            cs.setId(getRandom());
            cs.setImage_array(imagearray.toString());
            MainBaseApplication.getDBinstance().myDAO().addDummyData(cs);
            return cs;
        }

        @Override
        protected void onPostExecute(carmodel cm) {
            super.onPostExecute(cm);
            if (cm != null) {
                Toast.makeText(mcontext, "Saved successfully", Toast.LENGTH_SHORT).show();
                ((MainActivity)mcontext).loadHomeScreen();
            }
        }
    }

    private int getRandom() {
        Random r = new Random();
        return r.nextInt((999 - 1) + 1) + 1;
    }

    public static boolean hasPermissions(Context context) {
        String[] PERMISSIONS = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};
        if (context != null && PERMISSIONS != null) {
            for (String permission : PERMISSIONS) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void requestPermission() {
        String[] PERMISSIONS = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};
        requestPermissions(PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                imagearray = new JSONArray();
                if (returnValue != null && returnValue.size() > 0) {
                    for (int i = 0; i < returnValue.size(); i++) {
                        imagearray.put(returnValue.get(i));
                    }
                    attachtv.setText("Remove all  X" + "\n"+returnValue.size()+" Photo(s) selected");
                } else {
                    Toast.makeText(mcontext, "Select a valid image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean permissionGranted = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    permissionGranted = false;
                }
            }
            if (permissionGranted) {
                Pix.start(postadfragment.this,                    //Activity or Fragment Instance
                        SELECT_FILE,                //Request code for activity results
                        5);
            } else {
                Toast.makeText(mcontext, "App permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean checkModel() {
        if(TextUtils.isEmpty(input_name.getText().toString().trim())){
            input_name.setError("Cannot be empty");
            return false;
        }
        if(TextUtils.isEmpty(input_md.getText().toString().trim())){
            input_md.setError("Cannot be empty");
            return false;
        }
        if(TextUtils.isEmpty(input_ins.getText().toString().trim())){
            input_ins.setError("Cannot be empty");
            return false;
        }
        if(TextUtils.isEmpty(input_color.getText().toString().trim())){
            input_color.setError("Cannot be empty");
            return false;
        }
        if(TextUtils.isEmpty(input_km.getText().toString().trim())){
            input_km.setError("Cannot be empty");
            return false;
        }
        if(TextUtils.isEmpty(input_ft.getText().toString().trim())){
            input_ft.setError("Cannot be empty");
            return false;
        }
        if(TextUtils.isEmpty(input_ca.getText().toString().trim())){
            input_ca.setError("Cannot be empty");
            return false;
        }
        if(TextUtils.isEmpty(input_price.getText().toString().trim())){
            input_price.setError("Cannot be empty");
            return false;
        }
        if(imagearray==null || imagearray.length()==0){
            Toast.makeText(mcontext,"Please attach atleast 1 photo.",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
