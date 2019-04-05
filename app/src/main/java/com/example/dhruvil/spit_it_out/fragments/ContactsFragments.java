package com.example.dhruvil.spit_it_out.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhruvil.spit_it_out.Models.MyDBModel;
import com.example.dhruvil.spit_it_out.MyBounceInterpolator;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.Sqllite.DatabaseHelper;
import com.example.dhruvil.spit_it_out.activitys.ContectsActivity;
import com.example.dhruvil.spit_it_out.adapter.GroupListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragments extends Fragment {
    Button button,button1;
    TextView tvgroup;
    DatabaseHelper databaseHelper;
    RecyclerView listView;
    Context context;
    List<MyDBModel> grouplist;
    String[] numbers;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        tvgroup = view.findViewById(R.id.tvgroupname);
        button = view.findViewById(R.id.button);
        context=getActivity();
        listView = view.findViewById(R.id.listallgroup);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, ContectsActivity.class).putExtra("creategroup",false));


            }
        });



        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        fillistview();

    }

    public void fillistview() {
        databaseHelper = new DatabaseHelper(context);
        grouplist = databaseHelper.getAllgroups();
        if (grouplist.size() > 0) {

            GroupListAdapter adapter = new GroupListAdapter(grouplist, context);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            listView.setLayoutManager(linearLayoutManager);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setClickable(true);
            listView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, ContectsActivity.class).putExtra("from item",true));

                }
            });
        }
        else{
            Toast.makeText(context, "you not have any groups", Toast.LENGTH_SHORT).show();
        }
    }
}






