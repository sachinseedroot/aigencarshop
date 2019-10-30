package com.aigen.carshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aigen.carshop.BaseFragment;
import com.aigen.carshop.R;
import com.aigen.carshop.adapter.carlistadapter;
import com.aigen.carshop.controller.MainBaseApplication;
import com.aigen.carshop.db.model.carmodel;
import com.aigen.carshop.utilities.apputilities;
import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class homefragment extends BaseFragment {


    private RecyclerView carlistrecycler;
    private FloatingActionButtonExpandable fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return getView() != null ? getView() : inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        carlistrecycler = (RecyclerView) view.findViewById(R.id.rv_car_home_list);
        fab = (FloatingActionButtonExpandable) view.findViewById(R.id.fab);

        carlistrecycler.setHasFixedSize(true);
        carlistrecycler.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false));
        carlistrecycler.setItemAnimator(new DefaultItemAnimator());

        LiveData<List<carmodel>> carlistObserver = MainBaseApplication.getDBinstance().myDAO().getAllCarList();
        carlistObserver.observe(this, new Observer<List<carmodel>>() {
            @Override
            public void onChanged(List<carmodel> carmodels) {
                Collections.sort(carmodels, new Comparator<carmodel>() {
                    @Override
                    public int compare(carmodel cs, carmodel t1) {

                        try {
                            Date d1 = apputilities.getDateFromString(cs.getAdded_on());
                            Date d2 = apputilities.getDateFromString(t1.getAdded_on());
                            return d2.compareTo(d1);
                        }catch (Exception e){
                            e.printStackTrace();
                            return 0;
                        }
                    }
                });
                carlistrecycler.setAdapter(new carlistadapter(getContext(), carmodels, new carlistadapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, Bundle bl) {
                        loadFragment(view, bl, R.id.singlead_fragment, 0, true);
                    }
                }));
            }
        });


        //for post ad
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(v, new Bundle(), R.id.post_fragment, 0, true);
            }
        });
    }


}
