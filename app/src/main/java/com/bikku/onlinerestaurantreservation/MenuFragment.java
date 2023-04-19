package com.bikku.onlinerestaurantreservation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MenuFragment extends Fragment {

    RecyclerView recyclerView1;
    menuMainAdapter1 menuMainAdapter1;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        getActivity().setTitle("Food (Menu)");

        recyclerView1 = (RecyclerView)view.findViewById(R.id.rv1food);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView1.setHasFixedSize(true);

        FirebaseRecyclerOptions<MainModel1> options =
                new FirebaseRecyclerOptions.Builder<MainModel1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Food"), MainModel1.class)
                        .build();


        menuMainAdapter1 = new menuMainAdapter1(options);
        recyclerView1.setAdapter(menuMainAdapter1);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        menuMainAdapter1.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        menuMainAdapter1.stopListening();
    }
}
