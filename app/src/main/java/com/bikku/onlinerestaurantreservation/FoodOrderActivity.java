package com.bikku.onlinerestaurantreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodOrderActivity extends AppCompatActivity {

    // Define Order class within FoodOrderActivity
    private static class Order {
        private String name;
        private String phone;
        private String email;
        private String quantity;
        private String furl;

        public Order(String name, String phone, String email, String quantity, String foodImageUrl) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.quantity = quantity;
            this.furl = foodImageUrl;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getQuantity() {
            return quantity;
        }

        public String getFurl() {
            return furl;
        }
    }

    private ImageView img1;
    private TextView name, price, description;
    private EditText foodNameEditText, phoneEditText, emailEditText, quantiryEditText;
    private Button orderBtn;
    private String foodImageUrl, Name, Description;
    private double Price;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_food_order);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ordersRef = database.getReference("Order");

        // Find views by id
        img1 = findViewById(R.id.img1);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        description = findViewById(R.id.foodDescription);
        foodNameEditText = findViewById(R.id.Name);
        phoneEditText = findViewById(R.id.phoneNumber);
        emailEditText = findViewById(R.id.Email);
        quantiryEditText = findViewById(R.id.Quantity);
        orderBtn = findViewById(R.id.orderBtn);

        Intent intent = getIntent();
        foodImageUrl = intent.getStringExtra("furl");
        Name = intent.getStringExtra("name");
        Price = intent.getDoubleExtra("price", 0.0);
        price.setText(String.format("%.2f", Price));
        Description = intent.getStringExtra("description");

        // Load image using Glide library
        Glide.with(this)
                .load(foodImageUrl)
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .override(600, 600)
                .fitCenter()
                .into(img1);
        name.setText(Name);
        description.setText(Description);

        // Set click listener on book button
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user inputs
                String name = foodNameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String quantity = quantiryEditText.getText().toString();

                // Create order object and push to database
                Order order = new Order(name, phone, email, quantity, foodImageUrl);
                ordersRef.push().setValue(order);

                Toast.makeText(FoodOrderActivity.this, "Order placed successfully!", Toast.LENGTH_SHORT).show();

            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FoodOrderActivity.this, UserProfileActivity.class);
        startActivity(intent);
        finish();
    }
}

