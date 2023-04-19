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

public class AddActivity extends AppCompatActivity {

    EditText name, price, description, furl;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add);

        name = (EditText)findViewById(R.id.txtName);
        price = (EditText)findViewById(R.id.txtPrice);
        description = (EditText)findViewById(R.id.txtDescription);
        furl = (EditText)findViewById(R.id.txtImageUrl);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });
      /*  btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

    }
    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("price", Double.parseDouble(price.getText().toString()));
        map.put("description",description.getText().toString());
        map.put("furl",furl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Food").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Error while Inserting.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void clearAll(){
        name.setText("");
        price.setText("");
        description.setText("");
        furl.setText("");
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddActivity.this, FoodActivity.class);
        startActivity(intent);
        finish();
    }
}