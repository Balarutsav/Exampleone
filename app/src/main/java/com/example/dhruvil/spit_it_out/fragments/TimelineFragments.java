package com.example.dhruvil.spit_it_out.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhruvil.spit_it_out.Models.timelinespit;
import com.example.dhruvil.spit_it_out.Models.timeline;


import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.adapter.Myadapter;
import com.example.dhruvil.spit_it_out.webservices.RetrofitClient;
import com.example.dhruvil.spit_it_out.adapter.timelineadapter;
import com.example.dhruvil.spit_it_out.webservices.RetrofitInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimelineFragments extends Fragment {
    ArrayList<timelinespit> privatespit;
    timelineadapter timelineadapter;
    Context context;
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=  inflater.inflate(R.layout.fragment2,container,false);
        initViews();
        getprivatespits();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Public");
    }
    public void getprivatespits() {
        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<timeline> call = retrofitInterface.getprivatespit();
        call.enqueue(new Callback<timeline>() {
            @Override
            public void onResponse(Call<timeline> call, Response<timeline> response) {
                timeline model = response.body();
                if (model != null && model.getSpits() != null) {
                    privatespit.clear();
                    privatespit.addAll(model.getSpits());
                    timelineadapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<timeline > call, Throwable t) {

            }
        });
    }
    public void initViews() {

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview1);
        privatespit = new ArrayList<>();
        timelineadapter = new timelineadapter(privatespit,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(timelineadapter);

    }

}
