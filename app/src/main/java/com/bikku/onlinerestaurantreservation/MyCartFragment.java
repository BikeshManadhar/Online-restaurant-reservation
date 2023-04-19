package com.bikku.onlinerestaurantreservation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class MyCartFragment extends Fragment {
    RecyclerView recyclerViewCart;
    CartMainAdapter CartMainAdapter;


    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        // Set the title for this fragment
        getActivity().setTitle("My Cart");

        recyclerViewCart = (RecyclerView)view.findViewById(R.id.rv1Cart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCart.setHasFixedSize(true);
        FirebaseRecyclerOptions<CartMainModel> options =
                new FirebaseRecyclerOptions.Builder<CartMainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Order"), CartMainModel.class)
                        .build();


        CartMainAdapter = new CartMainAdapter(options);
        recyclerViewCart.setAdapter(CartMainAdapter);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        CartMainAdapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        CartMainAdapter.stopListening();
    }
}