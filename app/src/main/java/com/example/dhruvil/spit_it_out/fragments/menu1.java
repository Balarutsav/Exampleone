package com.example.dhruvil.spit_it_out.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhruvil.spit_it_out.Models.Datamodel;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.adapter.Myadapter;

import java.util.ArrayList;

public class menu1 extends Fragment {
    private final String android_image_urls[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png",
    };
    View view;
    Context context;
    ArrayList<Datamodel> android_verion ;
    Myadapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        context = getActivity();
        getActivity().setTitle("Timeline");
        android_verion = new ArrayList<>();
        initViews();
        return view;

    }



    public void initViews() {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new Myadapter(context, android_verion);
        recyclerView.setAdapter(adapter);

        prepareData();
    }


    private void prepareData() {

            for (int i = 0; i < android_image_urls.length; i++) {
            Datamodel androidversion = new Datamodel("image", android_image_urls[i]);
            android_verion.add(androidversion);
        }

        adapter.notifyDataSetChanged();

    }



}