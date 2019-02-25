package com.example.dhruvil.spit_it_out.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhruvil.spit_it_out.Models.Mobile;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.webservices.RetrofitClient;
import com.example.dhruvil.spit_it_out.webservices.RetrofitInterface;
import com.google.gson.JsonElement;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OTP_verification extends AppCompatActivity {
    Button button1;
    String phonenum;
    EditText editText1, editText2, editText3, editText4;
    ImageView imageView;
    String otpinput, checkNum, phone_to_confirm;
    TextView otpcode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        findview();
        setlistner();
        otp();
        init();
        registerApi();
        sendconfirmation();
    }

    private void setlistner() {

        otpcode.setText(phonenum);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpinput = editText1.getText().toString() + editText2.getText().toString() + editText3.getText().toString() + editText4.getText().toString();
                sendotp();

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                Intent intent = new Intent(OTP_verification.this, LoginActivity.class);
                startActivity(intent);


            }

        });
    }

    public void registerApi() {
        Mobile mobile = new Mobile();
        mobile.setRegid(1);
        mobile.setTel(phone_to_confirm);
        mobile.setPlatform("Android");
        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<Mobile> call = retrofitInterface.registeruser(mobile.getRegid(), mobile.tel, mobile.platform);
        call.enqueue(new Callback<Mobile>() {
            @Override
            public void onResponse(Call<Mobile> call, Response<Mobile> response) {
                Mobile mobile1 = response.body();
                mobile1.getRegid();
                Toast.makeText(OTP_verification.this, "responce suceess", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Mobile> call, Throwable t) {
            }
        });

    }

    public void sendotp() {
        Mobile mobile = new Mobile();
        mobile.setTo(phone_to_confirm);
        mobile.setToken(checkNum);
        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<JsonElement> call = retrofitInterface.gettoken(mobile.getTo(), mobile.getToken());
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (otpinput.equals(checkNum)) {
                    startActivity(new Intent(OTP_verification.this, MainActivity.class));
                } else {
                    Toast.makeText(OTP_verification.this, "plese enter correct otp", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(OTP_verification.this, "otpApi is working", Toast.LENGTH_LONG).show();
            }

            @Override

            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(OTP_verification.this, "otpApi is not working", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findview() {

        button1 = findViewById(R.id.next1);
        editText1 = findViewById(R.id.otp1);
        editText2 = findViewById(R.id.otp2);
        editText3 = findViewById(R.id.otp3);
        editText4 = findViewById(R.id.otp4);
        imageView = findViewById(R.id.back);
        otpcode = findViewById(R.id.OTP);
    }

    private void sendconfirmation() {
        checkNum = "";

        final Random rand = new Random();
        int min = 1000;
        int max = 9999;

        Log.w("confirmation", "sending confirmation sms to :" + phone_to_confirm);

        checkNum = Integer.toString(rand.nextInt((max - min) + 1) + min);
        otpcode.setText(checkNum);

    }

    public void otp() {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText1 != null) {
                    editText2.requestFocus();
                }
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText2 != null) {
                    editText3.requestFocus();
                }
            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText3 != null) {
                    editText4.requestFocus();
                }
            }
        });
    }

    private void init() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            phone_to_confirm = bundle.getString("phone_to_confirm");
            Log.e("phone_to_confirm", phone_to_confirm);
        }
    }

}
