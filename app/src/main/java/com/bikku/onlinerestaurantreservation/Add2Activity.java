package com.bikku.onlinerestaurantreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add2Activity extends AppCompatActivity {

    EditText t_type, t_capacity, t_location, table_description, turl;
    Button btnAdd2, btnBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add2);

        t_type = (EditText)findViewById(R.id.txtType2);
        t_capacity = (EditText)findViewById(R.id.txtCapacity2);
        t_location = (EditText)findViewById(R.id.txtLocation2);
        table_description = (EditText)findViewById(R.id.txtDescription2);
        turl = (EditText)findViewById(R.id.txtImageUrl2);

        btnAdd2 = (Button)findViewById(R.id.btnAdd2);
        btnBack2 = (Button)findViewById(R.id.btnBack2);

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });
        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("t_type", t_type.getText().toString());
        map.put("t_capacity", t_capacity.getText().toString());
        map.put("t_location", t_location.getText().toString());
        map.put("table_description",table_description.getText().toString());
        map.put("turl",turl.getText().toString());
        map.put("t_status", null);

        FirebaseDatabase.getInstance().getReference().child("Table").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Add2Activity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Add2Activity.this, "Error while Inserting.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clearAll(){
        t_type.setText("");
        t_capacity.setText("");
        t_location.setText("");
        table_description.setText("");
        turl.setText("");
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Add2Activity.this, TableActivity.class);
        startActivity(intent);
        finish();
    }
}