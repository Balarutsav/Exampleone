package com.example.dhruvil.spit_it_out.activitys;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dhruvil.spit_it_out.Models.MyDBModel;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.Sqllite.DatabaseHelper;
import com.example.dhruvil.spit_it_out.adapter.ShareSpitGroupAdapter;

import java.io.IOException;
import java.util.List;

public class ShareActivity extends AppCompatActivity {
    ImageView ivimage, ivaudio;
    TextView tvcancel;
    Context context;
    Button  btnAddGroup;
    RadioGroup rgsharespit;
    Uri images, video;
    String Audio;
    Intent intent;
    VideoView vvvideo;
    RecyclerView rvgrouplist;
    List<MyDBModel> grouplist;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        context = getApplicationContext();
        intent = getIntent();
        findview();
        setlistener();
        fillistview();

    }
    public void onResume() {
        super.onResume();
        fillistview();
    }

    private void setlistener() {
        if (intent.getBooleanExtra("type", false)) {
            images = Uri.parse(intent.getStringExtra("image"));
            ivimage.setImageURI(images);
            ivaudio.setVisibility(View.GONE);
            vvvideo.setVisibility(View.GONE);
        } else if (intent.getBooleanExtra("videotype", false)) {
            video = Uri.parse(intent.getStringExtra("Video"));
            ivimage.setVisibility(View.GONE);
            ivaudio.setVisibility(View.GONE);
            vvvideo.setMediaController(new MediaController(this));
            vvvideo.setVideoURI(video);
            vvvideo.requestFocus();
            vvvideo.start();


        } else {
            Audio=intent.getStringExtra("AudioUri");
            ivaudio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mediaPlayer=new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(Audio);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Toast.makeText(context, Audio+"is playing", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Toast.makeText(context, "thats not working", Toast.LENGTH_SHORT).show();
        }
        rgsharespit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(R.id.rballofthese);
                if (rb.isChecked()) {
                    Toast.makeText(ShareActivity.this, "rb 1 is cheked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ShareActivity.this, "except these is checked", Toast.LENGTH_SHORT).show();
                }


            }
        });
    tvcancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    btnAddGroup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(ShareActivity.this,ContectsActivity.class));
        }
    });
    }

    private void findview() {
        btnAddGroup=findViewById(R.id.btnAddGroup);
        ivaudio = findViewById(R.id.ivaudioshare);
        tvcancel=findViewById(R.id.tvCancel);
        vvvideo = findViewById(R.id.vvsharevideo);
        ivimage = findViewById(R.id.ivshareimage);
        rvgrouplist = findViewById(R.id.rvgrouplist);
        rgsharespit = findViewById(R.id.rggroup);

    }

    public void fillistview() {
        databaseHelper = new DatabaseHelper(context);
        grouplist = databaseHelper.getAllgroups();
        if (grouplist.size() > 0) {
            ShareSpitGroupAdapter adapter = new ShareSpitGroupAdapter(grouplist, context);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            rvgrouplist.setLayoutManager(linearLayoutManager);
            rvgrouplist.setAdapter(adapter);
        }
    }
}
