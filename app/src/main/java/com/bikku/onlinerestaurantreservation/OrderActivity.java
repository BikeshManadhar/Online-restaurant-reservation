package com.bikku.onlinerestaurantreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bikku.onlinerestaurantreservation.Adapters.OrdersAdapter;
import com.bikku.onlinerestaurantreservation.Models.OrdersModel;
import com.bikku.onlinerestaurantreservation.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<OrdersModel> list = new ArrayList<>();
        list.add(new OrdersModel(R.drawable.burger , "cheese Burger", "4" , "0000000"));
        list.add(new OrdersModel(R.drawable.burger , "cheese Burger", "4" , "0000000"));
        list.add(new OrdersModel(R.drawable.burger , "cheese Burger", "4" , "0000000"));
        list.add(new OrdersModel(R.drawable.burger , "cheese Burger", "4" , "0000000"));
        list.add(new OrdersModel(R.drawable.burger , "cheese Burger", "4" , "0000000"));
        list.add(new OrdersModel(R.drawable.burger , "cheese Burger", "4" , "0000000"));
        list.add(new OrdersModel(R.drawable.burger , "cheese Burger", "4" , "0000000"));
        list.add(new OrdersModel(R.drawable.burger , "cheese Burger", "4" , "0000000"));


        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);

    }
}