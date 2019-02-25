package com.example.dhruvil.spit_it_out.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dhruvil.spit_it_out.R;
import com.hbb20.CountryCodePicker;

import io.michaelrocks.libphonenumber.android.Phonenumber;



public class LoginActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    String phonenum;
    CountryCodePicker countryCodePicker;
    Phonenumber.PhoneNumber phoneNumber;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findview();
        listener();
        init();


    }

    private void listener() {
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {

            public void onCountrySelected() {

                showKeyboard(editText);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                if (editText != null) {
                    String phonenum = "+" + countryCodePicker.getSelectedCountryCode() + editText.getText().toString();
                    isValidPhone(phonenum);

                    startActivity(new Intent(LoginActivity.this, OTP_verification.class).putExtra("phone_to_confirm", phonenum));


                } else {
                    Toast.makeText(LoginActivity.this, "Plese enter yout number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void findview() {
        button = findViewById(R.id.buttonN);
        editText = findViewById(R.id.edittext1);
        phonenum = editText.getText().toString();
        countryCodePicker = findViewById(R.id.ccp);
    }

    public boolean isValidPhone(CharSequence phone) {
        if (TextUtils.isEmpty(phone)) return false;
        else {
            return android.util.Patterns.PHONE.matcher(phone).matches();

        }
    }

    public void init() {
        String locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0).getCountry();
        } else {
            locale = getResources().getConfiguration().locale.getCountry();
        }
        countryCodePicker.setCountryForNameCode(locale);


    }

    public void showKeyboard(final EditText ettext) {
        ettext.requestFocus();
        ettext.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                   keyboard.showSoftInput(ettext, 0);
                               }
                           }
                , 200);
    }
}


