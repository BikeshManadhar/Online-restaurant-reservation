package com.bikku.onlinerestaurantreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NaviagtionDrawer extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviagtion_drawer);
        setTitle("Home");


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });




        setSupportActionBar(toolbar);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F44336")));//this will change the action bar color

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.ColesDrawer);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();


        loadFragment(new AFragment());



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.optHome){
                   loadFragment(new AFragment());
                    Toast.makeText(NaviagtionDrawer.this, "Home", Toast.LENGTH_SHORT).show();
                }else if (id == R.id.optmenu){
                    loadFragment(new MenuFragment());
                    Toast.makeText(NaviagtionDrawer.this, "Menu", Toast.LENGTH_SHORT).show();
                }else if (id == R.id.optfavorite){
                    loadFragment(new FavoriteFragment());
                    Toast.makeText(NaviagtionDrawer.this, "Favorite", Toast.LENGTH_SHORT).show();
                }else if (id == R.id.optbookingtable){
                    loadFragment(new TablesFragment());
                    Toast.makeText(NaviagtionDrawer.this, "Booking Tables", Toast.LENGTH_SHORT).show();
                }else if (id == R.id.optbookingrooms){
                    loadFragment(new RoomsFragment());
                    Toast.makeText(NaviagtionDrawer.this, "Booking Rooms", Toast.LENGTH_SHORT).show();
                }else {
                    loadFragment(new MyCartFragment());
                    Toast.makeText(NaviagtionDrawer.this, "My Cart", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });


        

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container, fragment);
        ft.commit();


    }
}