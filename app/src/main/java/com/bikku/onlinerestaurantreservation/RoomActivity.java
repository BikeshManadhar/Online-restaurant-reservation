package com.bikku.onlinerestaurantreservation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class RoomActivity extends AppCompatActivity {

    RecyclerView recyclerView3;
    MainAdapter3 mainAdapter3;
    FloatingActionButton floatingActionButton3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //getSupportActionBar().setTitle("Food (Menu)");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_centered);

        // Get the TextView from the custom layout and set the title
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView actionBarTitle = findViewById(R.id.action_bar_title1);
        actionBarTitle.setText("Manage Room");

        recyclerView3 = (RecyclerView)findViewById(R.id.rv3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setHasFixedSize(true);

        FirebaseRecyclerOptions<MainModel3> options =
                new FirebaseRecyclerOptions.Builder<MainModel3>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Room"), MainModel3.class)
                        .build();

        mainAdapter3 = new MainAdapter3(options);
        recyclerView3.setAdapter(mainAdapter3);

        floatingActionButton3 = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddActivity3.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter3.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter3.stopListening();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private void txtSearch(String str)
    {
        String query = str.toLowerCase();
        FirebaseRecyclerOptions<MainModel3> options =
                new FirebaseRecyclerOptions.Builder<MainModel3>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Room").orderByChild("roomtype").startAt(query).endAt(query+"\uf8ff"), MainModel3.class)
                        .build();

        mainAdapter3 = new MainAdapter3(options);
        mainAdapter3.startListening();
        recyclerView3.setAdapter(mainAdapter3);

        mainAdapter3.notifyDataSetChanged();

    }
}