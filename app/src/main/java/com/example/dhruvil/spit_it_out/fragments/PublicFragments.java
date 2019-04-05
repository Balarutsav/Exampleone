package com.example.dhruvil.spit_it_out.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhruvil.spit_it_out.Models.Model;
import com.example.dhruvil.spit_it_out.Models.Spit;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.adapter.Myadapter;
import com.example.dhruvil.spit_it_out.webservices.RetrofitClient;
import com.example.dhruvil.spit_it_out.webservices.RetrofitInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicFragments extends Fragment {
    View view;
    Context context;
    ArrayList<Spit> publicspits;
    Myadapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        initViews();
        getPublicSpits();
        return view;

    }

    public void getPublicSpits() {
        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<Model> call = retrofitInterface.getpublicspit();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Model model = response.body();
                if (model != null && model.getSpits() != null) {
                    publicspits.clear();
                    publicspits.addAll(model.getSpits());
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });
    }


    public void initViews() {

            RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        publicspits = new ArrayList<>();
        adapter = new Myadapter(publicspits, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}
