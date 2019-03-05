package com.example.dhruvil.spit_it_out.activitys;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.dhruvil.spit_it_out.R;
import com.example.dhruvil.spit_it_out.Sqllite.DatabaseHelper;
import com.example.dhruvil.spit_it_out.fragments.ContactsFragments;
import com.example.dhruvil.spit_it_out.fragments.PublicFragments;
import com.example.dhruvil.spit_it_out.fragments.SettingsFragments;
import com.example.dhruvil.spit_it_out.fragments.TimelineFragments;

import net.alhazmy13.gota.Gota;
import net.alhazmy13.gota.GotaResponse;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener, Gota.OnRequestPermissionsBack {

    ImageView imageView;
    private DrawerLayout drawer;
    Fragment fragment = null;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);


        findview();

        requespermission();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(MainActivity.this,v);
                popupMenu.inflate(R.menu.popup);
          popupMenu.setOnMenuItemClickListener(MainActivity.this);
            popupMenu.show();}

        });
        NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        NavigationView navmenuview= (NavigationView) navigationView.getChildAt(0);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.s1, R.string.s2);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        fragment = new PublicFragments();

        if(fragment != null)
        {
            FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.f,fragment);
            ft.commit();
        }


    }

    private void findview() {
        imageView=findViewById(R.id.imgb1);
        drawer = findViewById(R.id.drawer_layout);
    }

    private void requespermission() {
        new Gota.Builder(this)
                .withPermissions(Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS)
                .requestId(1)
                .setListener(this)
                .check();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private void displaySelectedScreen(int id){
        drawer.closeDrawer(GravityCompat.START);

        switch (id)
        {
            case R.id.Public:
                fragment = new PublicFragments();
                break;
            case R.id.tieline:
                fragment = new TimelineFragments();
                break;
            case R.id.Contacts:
                fragment = new ContactsFragments();
                break;
            case R.id.Settings:
                fragment = new SettingsFragments();
                break;
        }
        if(fragment != null)
        {
            FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.f,fragment);
            ft.commit();
        }


    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Toast.makeText(this,"selected item:"+menuItem.getTitle(), Toast.LENGTH_LONG).show();

        switch (menuItem.getItemId()){
            case R.id.audio:
                Intent intentaudio = new Intent(MainActivity.this, AudioActivity.class);
                startActivity(intentaudio);

                return true;

            case R.id.camera:
                Intent intentcamera = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intentcamera);
                return true;

            case R.id.video:
                Intent intentvideo = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intentvideo);
                return true;
        }

        return false;
    }


    @Override
    public void onRequestBack(int requestId, @NonNull GotaResponse gotaResponse) {
        if(gotaResponse.isAllGranted()){
            Toast.makeText(MainActivity.this,"permission is granted",Toast.LENGTH_SHORT).show();
        }

        else
        {
            Toast.makeText(MainActivity.this,"give permission",Toast.LENGTH_SHORT).show();
        }
    }

}
