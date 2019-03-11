package com.example.dhruvil.spit_it_out.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhruvil.spit_it_out.Models.MyDBModel;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.activitys.ContectsActivity;

import java.util.List;

public class  GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.MyViewHolder> {
    Context context;

    private List<MyDBModel> datamodels;


    public GroupListAdapter(List<MyDBModel> datamodel, Context context) {
        this.context = context;

        this.datamodels = datamodel;
    }


    @Override
    public GroupListAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rawgroup, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GroupListAdapter.MyViewHolder myViewHolder, int i) {

        final MyDBModel myDBModel = datamodels.get(i);
        myViewHolder.mygroupname.setText(myDBModel.getName());
        myViewHolder.mygroupnumber.setText(myDBModel.getNumber());

        if(TextUtils.isEmpty(myDBModel.getNumber())){

            myViewHolder.mygroupnumber.setText("No members");
        }else {

            myViewHolder.mygroupnumber.setText(myDBModel.getNumber().split(",").length+" Members");
        }

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContectsActivity.class);
                intent.putExtra("groupname",myDBModel.getName());
                intent.putExtra("membersnumber",myDBModel.getNumber());
                intent.putExtra("itemclick",true);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return datamodels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mygroupname, mygroupnumber;


        MyViewHolder(View itemView) {
            super(itemView);
            mygroupname = itemView.findViewById(R.id.tvgroupname);
            mygroupnumber = itemView.findViewById(R.id.tvmembers);

        }

        @Override
        public void onClick(View v) {


        }
    }
}
