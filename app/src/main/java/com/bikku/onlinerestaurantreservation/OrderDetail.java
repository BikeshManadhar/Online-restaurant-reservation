package com.bikku.onlinerestaurantreservation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class OrderDetail extends AppCompatActivity {

    RecyclerView recyclerViewCart;
    CartMainAdapter CartMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_centered);

        // Get the TextView from the custom layout and set the title
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView actionBarTitle = findViewById(R.id.action_bar_title1);
        actionBarTitle.setText("Manage order details");
        actionBarTitle.setText("Manage order details");

        recyclerViewCart = findViewById(R.id.rv1Cart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setHasFixedSize(true);

        FirebaseRecyclerOptions<CartMainModel> options =
                new FirebaseRecyclerOptions.Builder<CartMainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Order"), CartMainModel.class)
                        .build();

        CartMainAdapter = new CartMainAdapter(options);
        recyclerViewCart.setAdapter(CartMainAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        CartMainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        CartMainAdapter.stopListening();
    }


}