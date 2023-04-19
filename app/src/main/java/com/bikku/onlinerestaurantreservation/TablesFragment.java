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


public class TablesFragment extends Fragment {
    RecyclerView recyclerView2;
    tableMainAdapter2 tableMainAdapter2;

    public TablesFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tables, container, false);

        // Set the title for this fragment
        getActivity().setTitle("Table Booking");

        recyclerView2 = (RecyclerView)view.findViewById(R.id.rv2order);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setHasFixedSize(true);

        FirebaseRecyclerOptions<MainModel2> options =
                new FirebaseRecyclerOptions.Builder<MainModel2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Table"), MainModel2.class)
                        .build();


        tableMainAdapter2 = new tableMainAdapter2(options);
        recyclerView2.setAdapter(tableMainAdapter2);

        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
        tableMainAdapter2.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        tableMainAdapter2.stopListening();
    }



}