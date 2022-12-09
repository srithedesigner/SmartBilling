package com.vaishu.smartbilling;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaishu.smartbilling.Classes.Customer;

public class registerActivity extends AppCompatActivity {

    Button regBtn;
    EditText username, password, mobileNumber;
    SwitchMaterial isAdmin;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://smartbilling-4aa28-default-rtdb.asia-southeast1.firebasedatabase.app/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regBtn = (Button) findViewById(R.id.registerbtn);
        username = (EditText) findViewById(R.id.userText);
        password = (EditText) findViewById(R.id.passwordText);
        mobileNumber = (EditText) findViewById(R.id.mobileText);
        isAdmin = findViewById(R.id.adminSwitch);

        DatabaseReference myRef = database.getReference("customers");
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Customer C = new Customer(username.getText().toString(), password.getText().toString(), mobileNumber.getText().toString(), isAdmin.isChecked());
                myRef.push().setValue(C);

                Toast.makeText(getApplicationContext(),
                        "User Added",
                        Toast.LENGTH_LONG).show();
                Intent I = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(I);
            }
        });





    }
}