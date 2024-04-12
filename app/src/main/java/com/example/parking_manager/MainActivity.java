package com.example.parking_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button peek,find,park,reset;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peek = findViewById(R.id.btn_peek);
        find = findViewById(R.id.btn_find);
        park = findViewById(R.id.btn_Park);
        reset = findViewById(R.id.rst_btn);

        peek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent GotoPeek = new Intent(getApplicationContext() , Parking_Area.class);
                startActivity(GotoPeek);
            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GotoFind = new Intent(getApplicationContext() , Find_Activity.class);
                startActivity(GotoFind);
            }
        });
        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GotoPark = new Intent(getApplicationContext() , park.class);
                startActivity(GotoPark);
                
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myref = database.getReference().child("ParkingArea");
                for(int a = 1 ; a < 5 ; a++)
                {
                    for(int b = 1 ; b < 7 ; b++)
                    {
                        myref.child(Integer.toString(a)).child(Integer.toString(b)).setValue(0);
                    }
                }
//                Intent gotosplash = new Intent(getApplicationContext() , splash1.class);
//                startActivity(gotosplash);
            }
        });

    }
}