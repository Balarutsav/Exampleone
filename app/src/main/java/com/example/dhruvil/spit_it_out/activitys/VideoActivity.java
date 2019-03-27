package com.example.dhruvil.spit_it_out.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.dhruvil.spit_it_out.R;

import java.io.File;

public class VideoActivity extends AppCompatActivity {
    Button recordvideo, playvideo;
    VideoView videoView;
    Uri videoFileUri;
    public static final int VIDEO_CAPTURED = 1;
    String folder_main = "Spit_It";
    Uri savevideo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        recordvideo = findViewById(R.id.buttonvideorecord);
        playvideo = findViewById(R.id.playVideo);
        videoView = findViewById(R.id.avideoview);

        recordvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                File video = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "Video");
                File image = new File(video, System.currentTimeMillis() + "VideoActivity.mp4");
                videoFileUri=Uri.fromFile(image);
                Intent recordvidero = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                recordvidero.putExtra(MediaStore.EXTRA_OUTPUT,videoFileUri);
                startActivityForResult(recordvidero, VIDEO_CAPTURED);

            }

        });
        playvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                videoView.setVideoURI(videoFileUri);
                videoView.start();

            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == VIDEO_CAPTURED) {
            videoView.setVideoURI(videoFileUri);
            videoView.setMediaController(new MediaController(this));
            videoView.requestFocus();
            videoView.start();

            startActivity(new Intent(VideoActivity.this,ShareActivity.class).putExtra("Video",videoFileUri.toString()).putExtra("VideoType",true));
        }
    }




}
