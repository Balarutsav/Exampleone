package com.example.dhruvil.spit_it_out.activitys;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dhruvil.spit_it_out.Models.ContactModel;
import com.example.dhruvil.spit_it_out.Models.groupmembers;
import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.adapter.CustomAdapter;

import java.util.ArrayList;

public class ContectsActivity extends AppCompatActivity {

    Button creategroup;
    EditText groupname;
    ArrayList<groupmembers> groupmembers;
    private ListView listView;
    private CustomAdapter customAdapter;
    private ArrayList<ContactModel> contactModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contects);

        listView = findViewById(R.id.listOfContacts);
        groupname = findViewById(R.id.editText_groupname);
        contactModelArrayList = new ArrayList<>();
        creategroup = findViewById(R.id.btnSpitItOut);
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactModel contactModel = new ContactModel();
            contactModel.setName(name);
            contactModel.setNumber(phoneNumber);
            contactModelArrayList.add(contactModel);

            Log.d("name>>", name + "  " + phoneNumber);
        }
        phones.close();
        if (getCallingActivity() == null) {
            customAdapter = new CustomAdapter(this, contactModelArrayList);
            listView.setAdapter(customAdapter);
        }
        creategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (groupname == null) {
                    Toast.makeText(ContectsActivity.this, "plese enter the group name", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContectsActivity.this, "groupcreate", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
