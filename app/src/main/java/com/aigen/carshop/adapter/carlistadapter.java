package com.aigen.carshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aigen.carshop.BaseActivity;
import com.aigen.carshop.R;
import com.aigen.carshop.db.model.carmodel;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class carlistadapter extends RecyclerView.Adapter<carlistadapter.ItemRowHolder> {


    private Context mContext;
    OnItemClickListener mItemClickListener;
    List<carmodel> dataList;

    public carlistadapter(Context context, List<carmodel> list, OnItemClickListener mItemClickListener) {
        this.dataList = list;
        this.mContext = context;
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        return new ItemRowHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.car_rec_item, viewGroup, false));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int position) {
        itemRowHolder.car_name_tv.setText(dataList.get(position).getCar_name());
        itemRowHolder.car_price_tv.setText(dataList.get(position).getPrice());
        itemRowHolder.totalkmtv.setText(dataList.get(position).getKm_driven());
        itemRowHolder.fueltypetv.setText(dataList.get(position).getFuel_type());
        itemRowHolder.cartypetv.setText(dataList.get(position).getCar_type());
        itemRowHolder.location_tv.setText(dataList.get(position).getCity_address());
        try {
            JSONArray jsonArray = new JSONArray(dataList.get(position).getImage_array());
            if (jsonArray != null && jsonArray.length() > 0) {
                ((BaseActivity) mContext).loadImage(mContext, jsonArray.getString(0), itemRowHolder.car_imgV);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }


    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        protected TextView car_name_tv, car_price_tv, totalkmtv, fueltypetv, cartypetv, location_tv;
        protected ImageView car_imgV;
        private Button get_details_tv;

        public ItemRowHolder(View rootView) {
            super(rootView);
            itemView.setOnClickListener(this);
            car_name_tv = (TextView) rootView.findViewById(R.id.car_name_tv);
            car_price_tv = (TextView) rootView.findViewById(R.id.car_price_tv);
            totalkmtv = (TextView) rootView.findViewById(R.id.totalkmtv);
            fueltypetv = (TextView) rootView.findViewById(R.id.fueltypetv);
            cartypetv = (TextView) rootView.findViewById(R.id.cartypetv);
            location_tv = (TextView) rootView.findViewById(R.id.location_tv);
            car_imgV = (ImageView) rootView.findViewById(R.id.car_imgV);
            get_details_tv = (Button) rootView.findViewById(R.id.get_details_tv);
            get_details_tv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", dataList.get(getAdapterPosition()).getId());
                mItemClickListener.onItemClick(v, getAdapterPosition(),bundle);
            }
        }
    }


    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, Bundle b);
    }

}