package com.example.dhruvil.spit_it_out.activitys;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.dhruvil.spit_it_out.R;

import net.alhazmy13.gota.Gota;
import net.alhazmy13.gota.GotaResponse;

import java.io.File;

public class SplashActivity extends AppCompatActivity implements Gota.OnRequestPermissionsBack {
    private static int splash=5000;
    String folder_main = "Spit_It";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        },splash);
        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }
        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "Images");
        if (!f1.exists()) {
            f1.mkdirs();
        }
        File f2 = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "Video");
        if (!f2.exists()) {
            f2.mkdirs();
        }
        File f3 = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "Audio");
        if (!f3.exists()) {
            f3.mkdirs();
        }



        new Gota.Builder(this)
                .withPermissions(Manifest.permission.INTERNET,Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .requestId(1)
                .setListener(this)
                .check();
    }


    @Override
    public void onRequestBack(int requestId, @NonNull GotaResponse gotaResponse) {
        if(gotaResponse.isAllGranted()){
            Toast.makeText(SplashActivity.this,"permission is granted",Toast.LENGTH_SHORT).show();
        }

            else
    {
            Toast.makeText(SplashActivity.this,"give permission",Toast.LENGTH_SHORT).show();
    }
    }


}
