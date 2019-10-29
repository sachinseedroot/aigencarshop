package com.aigen.carshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

    public carlistadapter(Context context, List<carmodel> list) {
        this.dataList = list;
        this.mContext = context;
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
        ((BaseActivity)mContext).loadImage(mContext,"https://auto.ndtvimg.com/car-images/colors/maruti-suzuki/dzire/maruti-suzuki-dzire-pearl-arctic-white.png",itemRowHolder.car_imgV);


    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class ItemRowHolder extends RecyclerView.ViewHolder{


        protected TextView car_name_tv, car_price_tv, totalkmtv, fueltypetv, cartypetv, location_tv;
        protected ImageView car_imgV;
        private Button get_details_tv;

        public ItemRowHolder(View rootView) {
            super(rootView);
            car_name_tv = (TextView) rootView.findViewById(R.id.car_name_tv);
            car_price_tv = (TextView) rootView.findViewById(R.id.car_price_tv);
            totalkmtv = (TextView) rootView.findViewById(R.id.totalkmtv);
            fueltypetv = (TextView) rootView.findViewById(R.id.fueltypetv);
            cartypetv = (TextView) rootView.findViewById(R.id.cartypetv);
            location_tv = (TextView) rootView.findViewById(R.id.location_tv);
            car_imgV = (ImageView) rootView.findViewById(R.id.car_imgV);
            get_details_tv = (Button) rootView.findViewById(R.id.get_details_tv);
        }
    }


    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, String specialization);
    }

}