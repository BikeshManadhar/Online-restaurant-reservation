package com.bikku.onlinerestaurantreservation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminDashboardActivity extends AppCompatActivity {

    Button btnFood, btnTable, btnRooms, btnLogout, btnOrderDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        //getSupportActionBar().setTitle("Food (Menu)");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_centered);

        // Get the TextView from the custom layout and set the title
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView actionBarTitle = findViewById(R.id.action_bar_title1);
        actionBarTitle.setText("Admin Dashboard");

        btnFood = (Button) findViewById(R.id.btnFood);
        btnTable = (Button) findViewById(R.id.btnTable);
        btnRooms = (Button) findViewById(R.id.btnRooms);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnOrderDetail = (Button) findViewById(R.id.btnOrderDetail);

        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                startActivity(intent);
            }
        });

        btnOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderDetail.class);
                startActivity(intent);
            }
        });

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TableActivity.class);
                startActivity(intent);
            }
        });
        btnRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });
       btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}