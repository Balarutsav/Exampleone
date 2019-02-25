package com.example.dhruvil.spit_it_out.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhruvil.spit_it_out.Models.Demomodel;
import com.example.dhruvil.spit_it_out.R;
import com.google.gson.Gson;

public class menu4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String json = "{\"status\": \"success\",\n" +
                "    \"success_data\": {\n" +
                "        \"user_id\": 70,\n" +
                "        \"name\": \"Rajesh\",\n" +
                "        \"username\": \"rajeshk.ips32@gmail.com\",\n" +
                "        \"credits\": \"\",\n" +
                "        \"access_token\": \"$2y$10$ZLX89HLhlJy9mMnBrZ8HuefplOWISM3in3EoB2puVIKtW6m9BowXK\",\n" +
                "        \"last_request_timestamp\": \"2019-02-18 13:09:47\",\n" +
                "        \"profile_pictures\": {\n" +
                "            \"thumbnail\": \"https://dablissdating.com/uploads/others/thumbnails/70_5c6696f196919_94869450.jpg\",\n" +
                "            \"encounter\": \"https://dablissdating.com/uploads/others/encounters/70_5c6696f196919_94869450.jpg\",\n" +
                "            \"other\": \"https://dablissdating.com/uploads/others/70_5c6696f196919_94869450.jpg\",\n" +
                "            \"original\": \"https://dablissdating.com/uploads/others/original/70_5c6696f196919_94869450.jpg\"\n" +
                "        },\n" +
                "        \"success_text\": \"User logged in successfully.\"\n" +
                "    }}";

        Gson gson = new Gson();

        Demomodel demomodel = gson.fromJson(json, Demomodel.class);

        return  inflater.inflate(R.layout.fragmet4,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Logout");
    }

}
