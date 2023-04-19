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

public class TableActivity extends AppCompatActivity {
    RecyclerView recyclerView2;
    MainAdapter2 mainAdapter2;
    FloatingActionButton floatingActionButton2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //getSupportActionBar().setTitle("Food (Menu)");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_centered);

        // Get the TextView from the custom layout and set the title
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView actionBarTitle = findViewById(R.id.action_bar_title1);
        actionBarTitle.setText("Manage Table");

        recyclerView2 = (RecyclerView)findViewById(R.id.rv2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setHasFixedSize(true);

        FirebaseRecyclerOptions<MainModel2> options =
                new FirebaseRecyclerOptions.Builder<MainModel2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Table"), MainModel2.class)
                        .build();


        mainAdapter2 = new MainAdapter2(options);
        recyclerView2.setAdapter(mainAdapter2);

        floatingActionButton2 = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Add2Activity.class));
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter2.stopListening();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        mainAdapter2.startListening();
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
        FirebaseRecyclerOptions<MainModel2> options =
                new FirebaseRecyclerOptions.Builder<MainModel2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Table").orderByChild("t_type").startAt(query).endAt(query+"\uf8ff"), MainModel2.class)
                        .build();

        mainAdapter2 = new MainAdapter2(options);
        mainAdapter2.startListening();
        recyclerView2.setAdapter(mainAdapter2);

        mainAdapter2.notifyDataSetChanged();

    }

}