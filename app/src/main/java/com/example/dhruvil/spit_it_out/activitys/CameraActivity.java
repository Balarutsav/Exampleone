package com.example.dhruvil.spit_it_out.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dhruvil.spit_it_out.R;

import java.io.File;

public class CameraActivity extends AppCompatActivity {
    Button camera,setimage;
    ImageView imageView;
    File image,images;
    private int requestCode=20;
    String folder_main = "Spit_It";
    Uri urisaveimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(Environment.getExternalStorageDirectory(), folder_main);
                images = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "Images");
               image=new File(images,System.currentTimeMillis()+"images.png");
                urisaveimage=Uri.fromFile(image);
                camera.putExtra(MediaStore.EXTRA_OUTPUT,urisaveimage);
                startActivityForResult(camera, requestCode);


    }
    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){

            startActivity(new Intent(CameraActivity.this,ShareActivity.class).putExtra("image",urisaveimage.toString()).putExtra("imagetype",true).putExtra("imagepath",image.toString()));

        }
    }

}

