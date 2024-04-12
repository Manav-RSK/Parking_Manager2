package com.example.parking_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fare extends AppCompatActivity {

    static int value = 0 ;
    Button book , amend ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);

        Intent getEx = getIntent();
        book = findViewById(R.id.bookbtn);
        amend = findViewById(R.id.amendbtn);

        String vno = getEx.getStringExtra("vno");
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fare.this, "Booked", Toast.LENGTH_SHORT).show();
                getemptyspace();
                int a = value/10;
                int b = value%10;
//                Toast.makeText(fare.this, Integer.toString(value), Toast.LENGTH_SHORT).show();
                database.getReference().child("ParkingArea").child(Integer.toString(a)).child(Integer.toString(b)).setValue(vno);

            }
        });

        amend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getemptyspace() {
        DatabaseReference myref = database.getReference().child("ParkingArea");
        myref.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    outer:
                    for(int a = 1 ; a < 5 ; a++)
                    {

                        for(int b = 1 ; b < 7 ; b++)
                        {
                            String carno = dataSnapshot.child(Integer.toString(a)).child(Integer.toString(b)).getValue().toString();
                            if (carno.equals("0"))
                            {
                                Intent getExtra = getIntent();
                                String vno = getExtra.getStringExtra("vno");
                                value = 10*a+b ;
                                int a1 = value/10;
                                int b1 = value%10;
                                Toast.makeText(fare.this, "XXXX"+ Integer.toString(a1)+Integer.toString(b1), Toast.LENGTH_SHORT).show();
                                database.getReference().child("ParkingArea").child(Integer.toString(a1)).child(Integer.toString(b1)).setValue(vno);
                                break outer;
                            }
                        }
                    }
                }
                else
                {
                    //nothing here
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors if any
            }
        });
//        Toast.makeText(fare.this, "before return " + Integer.toString(value), Toast.LENGTH_SHORT).show();
//        return value;
    }
}