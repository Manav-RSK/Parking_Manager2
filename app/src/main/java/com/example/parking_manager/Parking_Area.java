package com.example.parking_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Parking_Area extends AppCompatActivity {

    TextView r1 , r2 , r3 , r4 ;
    TextView c1 , c2 , c3 , c4 ,c5 , c6 ;
    ImageView v11 ,v12 , v13 , v14 , v21 , v22 , v23 , v24 , v31 , v32 , v33 , v34 , v41 , v42 , v43 , v44 , v51 , v52 , v53 , v54 , v61 , v62 , v63 , v64 ;
    TextView RC ;
    ImageView[][] Vehicle ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_area);


        RC = findViewById(R.id.RC);

        // Assuming these ImageView elements are part of your activity's layout
        v11 = findViewById(R.id.v11);
        v12 = findViewById(R.id.v12);
        v13 = findViewById(R.id.v13);
        v14 = findViewById(R.id.v14);
        v21 = findViewById(R.id.v21);
        v22 = findViewById(R.id.v22);
        v23 = findViewById(R.id.v23);
        v24 = findViewById(R.id.v24);
        v31 = findViewById(R.id.v31);
        v32 = findViewById(R.id.v32);
        v33 = findViewById(R.id.v33);
        v34 = findViewById(R.id.v34);
        v41 = findViewById(R.id.v41);
        v42 = findViewById(R.id.v42);
        v43 = findViewById(R.id.v43);
        v44 = findViewById(R.id.v44);
        v51 = findViewById(R.id.v51);
        v52 = findViewById(R.id.v52);
        v53 = findViewById(R.id.v53);
        v54 = findViewById(R.id.v54);
        v61 = findViewById(R.id.v61);
        v62 = findViewById(R.id.v62);
        v63 = findViewById(R.id.v63);
        v64 = findViewById(R.id.v64);
        Vehicle = new ImageView[][]{
                {v11, v12, v13, v14},
                {v21, v22, v23, v24},
                {v31, v32, v33, v34},
                {v41, v42, v43, v44},
                {v51, v52, v53, v54},
                {v61, v62, v63, v64}
        };


        r1 =findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        TextView Rows[] = {r1 , r2 , r3 , r4};

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        TextView Cols[] = {c1 , c2 , c3 , c4, c5 , c6};


        Intent getfromFind = getIntent() ;
        int location = getfromFind.getIntExtra("location" , 0);
        int r = ((int)location%10) ;
        int c = ((int)location/10) ;
        RC.setText("Row - "+Integer.toString(r)+" , Column - "+Integer.toString(c));
        setT();
        if(r != 0 && c != 0) {
            Rows[r - 1].setTextColor(getColor(R.color.Found));
            Cols[c - 1].setTextColor(getColor(R.color.Found));
            Vehicle[c-1][r-1].setImageResource(R.drawable.yourcar);
        }
    }

    private void setT() {
        DatabaseReference myref = database.getReference().child("ParkingArea");
        myref.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    for(int a = 1 ; a < 5 ; a++)
                    {
                        for(int b = 1 ; b < 7 ; b++)
                        {
                            String carno = dataSnapshot.child(Integer.toString(a)).child(Integer.toString(b)).getValue().toString();
                            if (carno.equals("0"))
                            {
                                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(Vehicle[b - 1][a - 1], "alpha", 1f, 0.35f);
                                fadeOut.setDuration(1000); // Adjust the duration as needed (in milliseconds)
                                fadeOut.start();
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
    }
}