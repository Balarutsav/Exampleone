package com.example.dhruvil.spit_it_out.activitys;

import android.app.ProgressDialog;
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
    Button buttonStart, buttonStop, buttonPlayLastRecordAudio,
            buttonStopPlayingRecording;
    MediaRecorder recorder;
    String saveaudio = null;
    MediaPlayer mediaPlayer;
    String audioname = System.currentTimeMillis() + "Audio.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        chronometer = findViewById(R.id.chronometer);
        buttonStart = findViewById(R.id.start);
        buttonStop = findViewById(R.id.stop);
        buttonPlayLastRecordAudio = findViewById(R.id.play);
        buttonStopPlayingRecording = findViewById(R.id.stopplay);

        final ProgressDialog progressDialog = new ProgressDialog(AudioActivity.this);
        progressDialog.setTitle("nothing");
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                buttonStart.setEnabled(false);
                buttonStop.setEnabled(true);

                Toast.makeText(AudioActivity.this, "Recording started", Toast.LENGTH_LONG).show();

                try {
                    recorder = new MediaRecorder();
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
                } catch (Exception e) {

                }
                File f = new File(Environment.getExternalStorageDirectory(), folder_main);
                File f3 = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "Audio");
                File Audio = new File(f3, audioname);
                saveaudio = (Audio).toString();
                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                Toast.makeText(AudioActivity.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
            }
        });
        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
                buttonStopPlayingRecording.setEnabled(true);
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(saveaudio);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(AudioActivity.this, "Recording Playing" + audioname,
                        Toast.LENGTH_LONG).show();
            }
        });
        buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();


                }
            }
        });
    }
}
