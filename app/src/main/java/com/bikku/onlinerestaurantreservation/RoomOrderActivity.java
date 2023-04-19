package com.bikku.onlinerestaurantreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RoomOrderActivity extends AppCompatActivity {

    private ImageView img3;
    private TextView roomno, roomcapacity, roomtype;
    private EditText FoodNameEditText, phoneEditText;
    private Button orderBtn;
    private String roomImageUrl, roomNo, roomCapacity, roomType;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find views by id
        img3 = findViewById(R.id.img3);
        roomno = findViewById(R.id.roomno);
        roomcapacity = findViewById(R.id.roomcapacity);
        roomtype = findViewById(R.id.roomtype);
        FoodNameEditText = findViewById(R.id.foodName);
        phoneEditText = findViewById(R.id.phoneNumber);
        orderBtn = findViewById(R.id.orderBtn);

        Intent intent = getIntent();
        roomImageUrl = intent.getStringExtra("rurl");
        roomNo = intent.getStringExtra("roomno");
        roomCapacity = intent.getStringExtra("roomcapacity");
        roomType = intent.getStringExtra("roomtype");

        // Load image using Glide library
        Glide.with(this)
                .load(roomImageUrl)
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .override(600, 600)
                .fitCenter()
                .into(img3);
        roomno.setText(roomNo);
        roomcapacity.setText(roomCapacity);
        roomtype.setText(roomType);

        // Set click listener on book button
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user inputs
                String name = FoodNameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RoomOrderActivity.this, UserProfileActivity.class);
        startActivity(intent);
        finish();

    }
}