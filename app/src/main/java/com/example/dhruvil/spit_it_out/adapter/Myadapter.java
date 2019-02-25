package com.example.dhruvil.spit_it_out.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dhruvil.spit_it_out.Models.Datamodel;
import com.example.dhruvil.spit_it_out.R;

import java.util.ArrayList;


public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    public Context context;
    private ArrayList<Datamodel> datamodel;

    public Myadapter(Context context, ArrayList<Datamodel> datamodel) {
        this.context = context;
        this.datamodel = datamodel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        if (datamodel.get(i).getType().equalsIgnoreCase("image")) {

            Glide.with(this.context)
                    .load(datamodel.get(i).getUrl()).into(myViewHolder.imageViewl);
        }else {
            myViewHolder.imageViewl.setImageResource(R.mipmap.ic_launcher);
        }

    }

    @Override
    public int getItemCount() {
        return datamodel.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder{
         ImageView imageViewl;

         MyViewHolder(View itemView) {
            super(itemView);


            imageViewl=itemView.findViewById(R.id.images);

        }
    }
}
