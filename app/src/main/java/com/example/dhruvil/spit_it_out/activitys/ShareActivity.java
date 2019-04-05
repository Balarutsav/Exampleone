package com.example.dhruvil.spit_it_out.activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dhruvil.spit_it_out.Models.MyDBModel;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.Sqllite.DatabaseHelper;
import com.example.dhruvil.spit_it_out.adapter.ShareSpitGroupAdapter;
import com.example.dhruvil.spit_it_out.webservices.RetrofitClient;
import com.example.dhruvil.spit_it_out.webservices.RetrofitInterface;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareActivity extends AppCompatActivity {
    ImageView ivimage, ivaudio,ivsharespit;
    EditText edtdescription;
    LinearLayout linearLayout;
    TextView tvcancel;
    Context context;
    Button  btnAddGroup;
    RadioGroup rgsharespit;
    String group;
    String groupnumbers;
    String filepath;
    Uri images, video;
    String Audio;
    Spinner spiviews;
    String type;
    Intent intent;
    VideoView vvvideo;
    RecyclerView rvgrouplist;
    List<MyDBModel> grouplist;
    DatabaseHelper databaseHelper;
    ShareSpitGroupAdapter adapter;

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
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void onResume() {
        super.onResume();
        fillistview();
    }

    private void setlistener() {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboardFrom(getApplicationContext(),v);

            }
        });

        ivsharespit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postapi();
                Toast.makeText(context, "your spit was uploading", Toast.LENGTH_SHORT).show();

            }
        });
        if (intent.getBooleanExtra("imagetype", false)) {
            images = Uri.parse(intent.getStringExtra("image"));
            ivimage.setImageURI(images);
            ivaudio.setVisibility(View.GONE);
            type="pic";
            filepath=intent.getStringExtra("imagepath");

            vvvideo.setVisibility(View.GONE);
        } else if (intent.getBooleanExtra("videotype", false)) {
            video = Uri.parse(intent.getStringExtra("Video"));
            ivimage.setVisibility(View.GONE);
            ivaudio.setVisibility(View.GONE);
            vvvideo.setMediaController(new MediaController(this));
            vvvideo.setVideoURI(video);
            vvvideo.requestFocus();
            vvvideo.start();
            type="video";
            filepath=intent.getStringExtra("videofilepath");



        } else {
            Audio=intent.getStringExtra("AudioUri");
            type="Audio";
            filepath=intent.getStringExtra("audiotype");
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
        linearLayout=findViewById(R.id.ll);
        ivsharespit=findViewById(R.id.ivsharespit);
        edtdescription=findViewById(R.id.edtspitdescription);
        spiviews=findViewById(R.id.spiviews);
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
            adapter = new ShareSpitGroupAdapter(grouplist, context);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            rvgrouplist.setLayoutManager(linearLayoutManager);
            rvgrouplist.setAdapter(adapter);
        }

    }
    /*public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            groupnumbers= intent.getStringExtra("groumembersnumbers");

        }
    };*/
    public void postapi() {

      /*  LocalBroadcastManager.getInstance(ShareActivity.this).registerReceiver(mMessageReceiver,new IntentFilter("selected-group"));
        Toast.makeText(context, groupnumbers, Toast.LENGTH_SHORT).show();
      */  File file = new File(filepath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        RequestBody regid = RequestBody.create(MediaType.parse("text/plain"),"C28C282684D15BC67A116716F380C311DB8F2B2BE5490E607CB51011FDD1DCECD56");
        RequestBody spinner = RequestBody.create(MediaType.parse("text/plain"),spiviews.getSelectedItem().toString());
        RequestBody groupmembers= RequestBody.create(MediaType.parse("text/plain"),adapter.numbers);
        RequestBody description= RequestBody.create(MediaType.parse("text/plain"), edtdescription.getText().toString());
        RequestBody signaturespitid = RequestBody.create(MediaType.parse("text/plain"), "0");
        RequestBody kind = RequestBody.create(MediaType.parse("text/plain"), type);



        Call<ResponseBody> call = retrofitInterface.postspit(regid,spinner,groupmembers,description,signaturespitid,kind,body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(ShareActivity.this, "post api onResponse", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(ShareActivity.this, "api resopne on fail", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
