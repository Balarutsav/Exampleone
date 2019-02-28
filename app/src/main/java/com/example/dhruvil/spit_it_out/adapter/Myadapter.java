package com.example.dhruvil.spit_it_out.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.dhruvil.spit_it_out.Models.Spit;
import com.example.dhruvil.spit_it_out.R;

import java.io.IOException;
import java.util.ArrayList;


public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    Context context;
    private ArrayList<Spit> datamodel;


    public Myadapter(ArrayList<Spit> datamodel, Context context) {
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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {


        if (datamodel.get(i).getKind().equals("pic")) {
            myViewHolder.videoView.setVisibility(View.GONE);
            myViewHolder.audio.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(datamodel.get(i).getPath())) {
                Glide.with(this.context).load(datamodel.get(i).getPath()).into(myViewHolder.imageViewl);
            } else {
                myViewHolder.imageViewl.setImageResource(R.mipmap.ic_launcher);
            }

        } else if (datamodel.get(i).getKind().equals("sound")) {
            myViewHolder.videoView.setVisibility(View.GONE);
            myViewHolder.imageViewl.setVisibility(View.GONE);
            final MediaPlayer mediaPlayer = new MediaPlayer();

            try {

                 mediaPlayer.setDataSource(datamodel.get(i).getPath());
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myViewHolder.audio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Sound play", Toast.LENGTH_LONG).show();
                    mediaPlayer.start();
                }
            });

        } else {

            myViewHolder.videoView.setVisibility(View.VISIBLE);
            myViewHolder.audio.setVisibility(View.GONE);
            myViewHolder.imageViewl.setVisibility(View.GONE);

        }


/*

        if (datamodel.get(i).getType().equalsIgnoreCase("image")) {

            Glide.with(this.context)
                    .load(datamodel.get(i).getUrl()).into(myViewHolder.imageViewl);
        }else {
            myViewHolder.imageViewl.setImageResource(R.mipmap.ic_launcher);
        }
        myViewHolder.tvdescription.setText("Hey ! this Spit you recieved xx days ago is now pulbic!");
        myViewHolder.tvdayago.setText("xxday");
*/

    }

    @Override
    public int getItemCount() {
        return datamodel.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewl, ivshare, ivinfo;
        TextView tvdescription, tvdayago;
        VideoView videoView;
        ImageButton audio;


        MyViewHolder(View itemView) {
            super(itemView);
            audio = itemView.findViewById(R.id.pfaudio);
            videoView = itemView.findViewById(R.id.pfvideoview);
            tvdescription = itemView.findViewById(R.id.tvdescription);
            tvdayago = itemView.findViewById(R.id.tvdayago);
            /*ivinfo=itemView.findViewById(R.id.ibaction);
            ivshare=itemView.findViewById(R.id.ibshare);*/
            imageViewl = itemView.findViewById(R.id.pfimageview);

        }
    }
}
