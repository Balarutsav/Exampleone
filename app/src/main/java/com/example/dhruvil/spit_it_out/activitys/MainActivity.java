package com.example.dhruvil.spit_it_out.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.example.dhruvil.spit_it_out.fragments.menu1;
import com.example.dhruvil.spit_it_out.fragments.menu2;
import com.example.dhruvil.spit_it_out.fragments.menu3;
import com.example.dhruvil.spit_it_out.fragments.menu4;

import net.alhazmy13.gota.Gota;
import net.alhazmy13.gota.GotaResponse;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener, Gota.OnRequestPermissionsBack {

    ImageView imageView;
    private DrawerLayout drawer;
    Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imgb1);
        drawer = findViewById(R.id.drawer_layout);

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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.s1, R.string.s2);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        fragment = new menu1();

        if(fragment != null)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.f,fragment);
            ft.commit();
        }


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
            case R.id.timeline:
                fragment = new menu1();
                break;
            case R.id.publict:
                fragment = new menu2();
                break;
            case R.id.viedo:
                fragment = new menu3();
                break;
            case R.id.logout:
                fragment = new menu4();
        }
        if(fragment != null)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
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
                Intent intent = new Intent(MainActivity.this, audio.class);
                startActivity(intent);

                return true;

            case R.id.camera:
                Intent intent2 = new Intent(MainActivity.this, camera.class);
                startActivity(intent2);
                return true;

            case R.id.video:
                Intent intent1=new Intent(MainActivity.this, video.class);
                startActivity(intent1);
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
