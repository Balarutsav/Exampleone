package com.example.dhruvil.spit_it_out.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhruvil.spit_it_out.Models.MyDBModel;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.activitys.ContectsActivity;

import java.util.List;

public class ShareSpitGroupAdapter extends RecyclerView.Adapter<ShareSpitGroupAdapter.MyViewHolder> {
    Context context;
    Intent intent;
    private List<MyDBModel> datamodels;
    public String numbers="";


    public ShareSpitGroupAdapter(List<MyDBModel> datamodel, Context context) {
        this.context = context;

        this.datamodels = datamodel;
    }


    @Override
    public ShareSpitGroupAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rawshareadapter, viewGroup, false);
     return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final MyDBModel myDBModel=datamodels.get(i);
        myViewHolder.cbselectgroup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(myViewHolder.cbselectgroup.isChecked()){
                    numbers=myDBModel.getNumber();
//                    intent=new Intent();
//                    intent.putExtra("groumembersnumbers",myDBModel.getNumber());
                 /*   LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                 */   Toast.makeText(context, myDBModel.getNumber(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.e("not cheked",myDBModel.getName());
                }

            }
        });
        if(TextUtils.isEmpty(myDBModel.getNumber())){

            myViewHolder.mygroupname.setText("No members");
        }else {

            myViewHolder.cbselectgroup.setChecked(false);
            myViewHolder.mygroupname.setText(myDBModel.getName() + "(" + myDBModel.getNumber().split(",").length + ")");
        }
    }



    @Override
    public int getItemCount() {
        return datamodels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mygroupname;
        CheckBox cbselectgroup;


        MyViewHolder(View itemView) {
            super(itemView);
            mygroupname = itemView.findViewById(R.id.tvmembers);
            cbselectgroup=itemView.findViewById(R.id.cbforgroup);


        }

        @Override
        public void onClick(View v) {


        }
    }
}
