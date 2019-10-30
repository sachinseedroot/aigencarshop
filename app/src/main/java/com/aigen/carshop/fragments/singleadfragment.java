package com.aigen.carshop.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.aigen.carshop.BaseFragment;
import com.aigen.carshop.R;
import com.aigen.carshop.adapter.carlistadapter;
import com.aigen.carshop.adapter.viewpageradapter;
import com.aigen.carshop.controller.MainBaseApplication;
import com.aigen.carshop.db.model.carmodel;

import org.json.JSONArray;

import java.util.List;

public class singleadfragment extends BaseFragment {


    private ViewPager viewPager;
    private TextView car_price_tv;
    private TextView car_name_tv;
    private int id;
    private LinearLayout sliderDots;
    private TextView cttv;
    private TextView mdtv;
    private TextView itv;
    private TextView colortv;
    private TextView kmtv;
    private TextView fttv;
    private TextView catv;
    private TextView aotv;
    private Button contactsellerbtn;

    public static singleadfragment newInstance(Bundle b1) {
        singleadfragment f = new singleadfragment();
        f.setArguments(b1);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        id =  getArguments().getInt("id");
        return getView() != null ? getView() : inflater.inflate(R.layout.fragment_single_ad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        new AsyncGetCarDetails().execute(id);

    }

    private void initView(View view){
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        sliderDots = (LinearLayout) view.findViewById(R.id.SliderDots);
        car_name_tv = (TextView)view.findViewById(R.id.car_name_tv);
        car_price_tv = (TextView)view.findViewById(R.id.car_price_tv);
        contactsellerbtn = (Button) view.findViewById(R.id.contact_seller_tv);
        cttv = (TextView)view.findViewById(R.id.cttv);
        mdtv = (TextView)view.findViewById(R.id.mdtv);
        itv = (TextView)view.findViewById(R.id.itv);
        colortv = (TextView)view.findViewById(R.id.colortv);
        kmtv = (TextView)view.findViewById(R.id.kmtv);
        fttv = (TextView)view.findViewById(R.id.fttv);
        catv = (TextView)view.findViewById(R.id.catv);
        aotv = (TextView)view.findViewById(R.id.aotv);

    }

    public class AsyncGetCarDetails extends AsyncTask<Integer,Void,carmodel>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sliderDots.removeAllViews();
        }

        @Override
        protected carmodel doInBackground(Integer... integers) {
            return MainBaseApplication.getDBinstance().myDAO().getSpecificCarDetail(integers[0]);
        }

        @Override
        protected void onPostExecute(final carmodel carmodel) {
            super.onPostExecute(carmodel);
            car_name_tv.setText(carmodel.getCar_name());
            car_price_tv.setText(carmodel.getPrice());
            cttv.setText(carmodel.getCar_type());
            mdtv.setText(carmodel.getManufactured_date());
            itv.setText(carmodel.getInsurance());
            colortv.setText(carmodel.getColor());
            kmtv.setText(carmodel.getKm_driven());
            fttv.setText(carmodel.getFuel_type());
            catv.setText(carmodel.getCity_address());
            aotv.setText(carmodel.getAdded_on());
            try{
                JSONArray jsonArray = new JSONArray(carmodel.getImage_array());
                for(int i=0;i<jsonArray.length();i++){
                    TextView textView = new TextView(mcontext);
                    textView.setText((i+1)+" ");
                    textView.setTextColor(ContextCompat.getColor(mcontext,R.color.lightgrey));
                    sliderDots.addView(textView);
                }
                viewpageradapter vp = new viewpageradapter(mcontext,jsonArray);
                viewPager.setAdapter(vp);
            }catch (Exception e){
                e.printStackTrace();
            }

            contactsellerbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialog = new contacdailogfragment();
                    dialog.show(getFragmentManager(), "dialog");
                }
            });

        }
    }

}
