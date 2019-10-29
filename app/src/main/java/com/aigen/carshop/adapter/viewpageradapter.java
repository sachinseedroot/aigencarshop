package com.aigen.carshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.aigen.carshop.BaseActivity;
import com.aigen.carshop.BaseFragment;
import com.aigen.carshop.R;

import org.json.JSONArray;
import org.json.JSONException;

public class viewpageradapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private JSONArray imageArray;


    public viewpageradapter(Context context, JSONArray jsonArray) {
        this.context = context;
        this.imageArray = jsonArray;
    }

    @Override
    public int getCount() {
        return imageArray.length();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_imageslider, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        try {
            ((BaseActivity)context).loadImage(context,imageArray.getString(position),imageView);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}