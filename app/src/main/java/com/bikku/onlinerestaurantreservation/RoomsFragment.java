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


public class RoomsFragment extends Fragment {
    RecyclerView recyclerView3;
    roomMainAdapter3 roomMainAdapter3;



    public RoomsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        getActivity().setTitle("Room Booking");

        recyclerView3 = (RecyclerView)view.findViewById(R.id.rv3room);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView3.setHasFixedSize(true);

        FirebaseRecyclerOptions<MainModel3> options =
                new FirebaseRecyclerOptions.Builder<MainModel3>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Room"), MainModel3.class)
                        .build();


        roomMainAdapter3 = new roomMainAdapter3(options);
        recyclerView3.setAdapter(roomMainAdapter3);

        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
        roomMainAdapter3.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        roomMainAdapter3.stopListening();
    }



}