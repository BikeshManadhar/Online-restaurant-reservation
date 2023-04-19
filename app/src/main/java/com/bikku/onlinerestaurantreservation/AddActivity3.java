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

public class AddActivity3 extends AppCompatActivity {
    EditText roomno3, roomcapacity3, roomtype3, rurl3;
    Button btnAdd3, btnBack3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add3);

        roomno3 = (EditText)findViewById(R.id.txtRoomNumber3);
        roomcapacity3 = (EditText)findViewById(R.id.txtCapacity3);
        roomtype3 = (EditText)findViewById(R.id.txtType3);
        rurl3 = (EditText)findViewById(R.id.txtImageUrl3);

        btnAdd3 = (Button)findViewById(R.id.btnAdd3);
        btnBack3 = (Button)findViewById(R.id.btnBack3);

        btnAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });
        btnBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("roomno", roomno3.getText().toString());
        map.put("roomcapacity", roomcapacity3.getText().toString());
        map.put("roomtype",roomtype3.getText().toString());
        map.put("rurl",rurl3.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Room").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity3.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity3.this, "Error while Inserting.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clearAll(){
        roomno3.setText("");
        roomcapacity3.setText("");
        roomtype3.setText("");
        rurl3.setText("");
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddActivity3.this, RoomActivity.class);
        startActivity(intent);
        finish();

    }
}