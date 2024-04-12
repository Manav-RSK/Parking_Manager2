package com.example.parking_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Find_Activity extends AppCompatActivity {

    Button search ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        EditText num = findViewById(R.id.editTextNumber);
        search = findViewById(R.id.searchBtn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    String text = num.getText().toString();
                    if(text.isEmpty())
                    {
                        Toast.makeText(Find_Activity.this, "Enter vehicle number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(text.equals("0"))
                    {
                        Toast.makeText(Find_Activity.this, "Enter Valid number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DatabaseReference myref = database.getReference().child("ParkingArea");
                    myref.addListenerForSingleValueEvent(new ValueEventListener(){
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists())
                            {
                                boolean notfound = true ;
                                outer:
                                for(int a = 1 ; a < 5 ; a ++)
                                {
                                    for(int b = 1 ; b < 7 ; b ++)
                                    {
                                        String vno = dataSnapshot.child(Integer.toString(a)).child(Integer.toString(b)).getValue().toString();
                                        if(vno.equalsIgnoreCase(text))
                                        {
                                            Toast.makeText(Find_Activity.this, "Row "+Integer.toString(a)+", Column "+Integer.toString(b), Toast.LENGTH_SHORT).show();
                                            notfound = false ;
                                            Intent gotoParking = new Intent(getApplicationContext() , Parking_Area.class);
                                            gotoParking.putExtra("location", (10*b)+a);
                                            startActivity(gotoParking);
                                            break outer;
                                        }
                                    }
                                }
                                if(notfound)
                                {
                                    Toast.makeText(Find_Activity.this, "Vehicle Not Found", Toast.LENGTH_SHORT).show();
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
                }
        });

    }
}