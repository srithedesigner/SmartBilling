package com.vaishu.smartbilling;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.vaishu.smartbilling.Classes.Product;

public class registerProducts extends AppCompatActivity {

    public static TextView barcodeValue;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://smartbilling-4aa28-default-rtdb.asia-southeast1.firebasedatabase.app/");
    public static ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_products);

        barcodeValue = findViewById(R.id.barcodeValue);
        progressBar = findViewById(R.id.progressBar);

        MaterialButton scanBtn = findViewById(R.id.scanbtn);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), scanningActivity.class));
            }
        });
        DatabaseReference myRef = database.getReference("products");
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.nameText)).getText().toString();
                float price = Float.parseFloat(((EditText)findViewById(R.id.priceText)).getText().toString());
                String barcode = ((TextView)findViewById(R.id.barcodeValue)).getText().toString();
                Product p = new Product(name,price, barcode);
                System.out.println(p);
                myRef.child(barcode).setValue(p);

            }
        });


    }
}