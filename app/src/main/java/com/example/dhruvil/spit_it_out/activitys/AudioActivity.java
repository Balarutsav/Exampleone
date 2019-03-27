package com.example.dhruvil.spit_it_out.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.dhruvil.spit_it_out.R;

import java.io.File;
import java.io.IOException;

public class AudioActivity extends AppCompatActivity {
    Chronometer chronometer;
    String folder_main = "Spit_It";
    Button buttonStart, buttonStop;
    MediaRecorder recorder;
    File Audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        chronometer = findViewById(R.id.chronometer);
        buttonStart = findViewById(R.id.start);
        buttonStop = findViewById(R.id.stop);


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                buttonStart.setEnabled(false);
                buttonStop.setEnabled(true);

                Toast.makeText(AudioActivity.this, "Recording started", Toast.LENGTH_LONG).show();

                try { File f3 = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "Audio");
                    Audio = new File(f3, System.currentTimeMillis() + "Audio.mp3");
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    recorder.setOutputFile(Audio.getPath());


                    chronometer.start();
                    recorder.prepare();
                    recorder.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                }
                chronometer.stop();
                try {
                    recorder.stop();
                    recorder.release();
                } catch (Exception e) {

                }
                startActivity(new Intent(AudioActivity.this,ShareActivity.class).putExtra("AudioUri",Audio.getPath()).putExtra("AudioType",true));

                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                Toast.makeText(AudioActivity.this, "Recording Completed", Toast.LENGTH_LONG).show();
            }
        });

    }
}
