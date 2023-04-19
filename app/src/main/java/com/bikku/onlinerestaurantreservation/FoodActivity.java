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

public class FoodActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter1 mainAdapter1;
    FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //getSupportActionBar().setTitle("Food (Menu)");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_centered);

        // Get the TextView from the custom layout and set the title
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView actionBarTitle = findViewById(R.id.action_bar_title1);
        actionBarTitle.setText("Manage Food(Menu)");

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        FirebaseRecyclerOptions<MainModel1> options =
                new FirebaseRecyclerOptions.Builder<MainModel1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Food"), MainModel1.class)
                        .build();

        mainAdapter1 = new MainAdapter1(options);
        recyclerView.setAdapter(mainAdapter1);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter1.stopListening();
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
        FirebaseRecyclerOptions<MainModel1> options =
                new FirebaseRecyclerOptions.Builder<MainModel1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Food").orderByChild("name").startAt(query).endAt(query+"\uf8ff"), MainModel1.class)
                        .build();

        mainAdapter1 = new MainAdapter1(options);
        mainAdapter1.startListening();
        recyclerView.setAdapter(mainAdapter1);

        mainAdapter1.notifyDataSetChanged();

    }
}