package com.example.dhruvil.spit_it_out.activitys;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dhruvil.spit_it_out.Models.ContactModel;
import com.example.dhruvil.spit_it_out.Models.MyDBModel;
import com.example.dhruvil.spit_it_out.Models.groupmembers;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.Sqllite.DatabaseHelper;
import com.example.dhruvil.spit_it_out.adapter.CustomAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContectsActivity extends AppCompatActivity {

    private ContactModel group_edit;
    Button btncreategroup,btndeletegroup;
    EditText edtgroupname;
    ArrayList<groupmembers> groupmembers;
    DatabaseHelper databaseHelper;
    private ListView listView;
    TextView tvcancel;
    private CustomAdapter customAdapter;
    private ArrayList<ContactModel> contactModelArrayList;
    private ArrayList<String> phoneList;
    private ArrayList<MyDBModel> myDBModels;
    Fragment fragment = null;
   LinearLayout view;
   ListView listofcontects;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contects);
        btndeletegroup=findViewById(R.id.btndeletegroup);
        tvcancel = findViewById(R.id.tvCancel);
        listofcontects=findViewById(R.id.listOfContacts);
        databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listOfContacts);
        edtgroupname = findViewById(R.id.editText_groupname);
        view=findViewById(R.id.llview);
        contactModelArrayList = new ArrayList<>();
        phoneList = new ArrayList<>();
        btncreategroup = findViewById(R.id.btnSpitItOut);
        tvcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Intent intent=getIntent();
        String groupname=intent.getStringExtra("groupname");
        String membersnumber=intent.getStringExtra("membersnumber");
        List<String> numbers = new ArrayList<>();

        if(intent.getBooleanExtra("itemclick",false)){
            edtgroupname.setText(groupname);
            numbers = Arrays.asList(membersnumber.split(","));
            /*phoneList=databaseHelper.deletegroup();*/

        }



        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactModel contactModel = new ContactModel();
            contactModel.setName(name);
            contactModel.setNumber(phoneNumber);
            if(numbers.contains(phoneNumber)){
                contactModel.setChecked(true);
            }else {
                contactModel.setChecked(false);
            }

            contactModelArrayList.add(contactModel);

            Log.d("name>>", name + "  " + phoneNumber);
        }
        phones.close();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(v);
            }
        });

        if (contactModelArrayList.size() > 0) {

            customAdapter = new CustomAdapter(this, contactModelArrayList);
            listView.setAdapter(customAdapter);

        }


        btncreategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtgroupname.getText().toString().trim())) {

                    Toast.makeText(ContectsActivity.this, "plese enter the group name", Toast.LENGTH_SHORT).show();
                    if(edtgroupname ==null){
                        Toast.makeText(ContectsActivity.this, "enter number", Toast.LENGTH_SHORT).show();
                    }else if(edtgroupname !=null){

                        finish();
                    }
                } else {


                    String phoneNumbers = "";
                    ArrayList<ContactModel> dataList = customAdapter.getContactModelArrayList();

                    for (int i = 0; i < dataList.size(); i++) {

                        if (dataList.get(i).isChecked()) {

                            if (TextUtils.isEmpty(phoneNumbers)) {
                                phoneNumbers = dataList.get(i).getNumber();
                            } else {
                                phoneNumbers = phoneNumbers + "," + dataList.get(i).getNumber();

                            }
                        }

                    }

                    databaseHelper.insertGroup(edtgroupname.getText().toString().trim(), phoneNumbers);

                    finish();
                }

            }
        });



    }
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
