package com.vaishu.smartbilling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaishu.smartbilling.Classes.CartElement;
import com.vaishu.smartbilling.Classes.Product;

import java.util.Objects;

public class addProduct extends AppCompatActivity {
    public static TextView barcodeValue;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://smartbilling-4aa28-default-rtdb.asia-southeast1.firebasedatabase.app/");
    public static ProgressBar progressBar;
    String User = "7893555399";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        barcodeValue = findViewById(R.id.barcodeValue);
        progressBar = findViewById(R.id.progressBar);
        EditText counter = (EditText)findViewById(R.id.counterText);



        MaterialButton scanBtn = findViewById(R.id.scanbtn);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), scanningAddingActivity.class));
            }
        });

        findViewById(R.id.plusbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.setText(Integer.parseInt(counter.getText().toString()) + 1);
            }
        });

        findViewById(R.id.minusbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(counter.getText().toString()) > 0)
                {
                    counter.setText(Integer.parseInt(counter.getText().toString()) - 1);
                }
            }
        });

        DatabaseReference myRef = database.getReference("products");
        DatabaseReference cartRef = database.getReference("Cart");

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String barcode = barcodeValue.getText().toString();
                System.out.println(barcode);
                int quantity = Integer.parseInt(((TextView)(findViewById(R.id.counterText))).getText().toString());

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren())
                        {

                            if(ds.getKey().equals(barcode))
                            {
                                System.out.println(ds.getKey());
                                Product p = ds.getValue(Product.class);
                                CartElement ce = new CartElement(quantity, p);

                                cartRef.child("7893555399").push().setValue(ce);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("Error!");
                    }
                });

            }
        });





    }
}