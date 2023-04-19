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

public class TableOrderActivity extends AppCompatActivity {

    private ImageView img2;
    private TextView type, capacity, location, DescriptionOrder;
    private EditText FoodNameEditText, phoneEditText;
    private Button orderBtn;
    private String tableImageUrl, tableType, tableCapacity, tableLocation, tableDescription;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find views by id
        img2 = findViewById(R.id.img2);
        type = findViewById(R.id.type);
        capacity = findViewById(R.id.capacity);
        location = findViewById(R.id.location);
        DescriptionOrder = findViewById(R.id.DescriptionOrder);
        FoodNameEditText = findViewById(R.id.foodName);
        phoneEditText = findViewById(R.id.phoneNumber);
        orderBtn = findViewById(R.id.orderBtn);

        Intent intent = getIntent();
        tableImageUrl = intent.getStringExtra("turl");
        tableType = intent.getStringExtra("t_type");
        tableCapacity = intent.getStringExtra("t_capacity");
        tableLocation = intent.getStringExtra("t_location");
        tableDescription = intent.getStringExtra("table_description");

        // Load image using Glide library
        Glide.with(this)
                .load(tableImageUrl)
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .override(600, 600)
                .fitCenter()
                .into(img2);

        type.setText(tableType);
        capacity.setText(tableCapacity);
        location.setText(tableLocation);
        DescriptionOrder.setText(tableDescription);

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
        Intent intent = new Intent(TableOrderActivity.this, UserProfileActivity.class);
        startActivity(intent);
        finish();
    }

    // Override onOptionsItemSelected() method to handle back button click
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Go back to TablesFragment
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TablesFragment tablesFragment = new TablesFragment();
                ft.replace(R.id.fragment_container, tablesFragment);
                ft.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
