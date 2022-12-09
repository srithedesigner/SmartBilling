package com.vaishu.smartbilling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaishu.smartbilling.Classes.Customer;

import java.lang.invoke.ConstantCallSite;

public class LoginActivity extends AppCompatActivity {

    Button regBtn, loginBtn;
    EditText username, password, mobileNumber;
    SwitchMaterial isAdmin;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://smartbilling-4aa28-default-rtdb.asia-southeast1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regBtn = (Button) findViewById(R.id.register);
        loginBtn = (Button) findViewById(R.id.loginbtn);
        username = (EditText) findViewById(R.id.userText);
        password = (EditText) findViewById(R.id.passwordText);
        mobileNumber = (EditText) findViewById(R.id.mobileText);
        isAdmin = findViewById(R.id.adminSwitch);

        DatabaseReference myRef = database.getReference("customers");

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getApplicationContext(), registerActivity.class);
                startActivity(I);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean Sucess = false;
                        for(DataSnapshot ds : snapshot.getChildren())
                        {
                            Customer c = ds.getValue(Customer.class);
                            assert c != null;
                            System.out.println("|||||||||||||" + c.getName());
                            System.out.println("|||||||||||||" + c.getPassword());
                            if(c.getName().equals(username.getText().toString()) && c.getPassword().equals(password.getText().toString()))
                            {
                                Sucess = true;
                                Toast.makeText(getApplicationContext(),
                                        "User Authentication Successful",
                                        Toast.LENGTH_LONG).show();

                                Intent I = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(I);
                            }
                        }

                        if(!Sucess)
                        {
                            Toast.makeText(getApplicationContext(),
                                    "User Authentication Failed, Try Again",
                                    Toast.LENGTH_LONG).show();

                            Intent I = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(I);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



    }
}