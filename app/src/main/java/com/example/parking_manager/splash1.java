package com.example.parking_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

        Intent sendExtra = getIntent() ;
        String val = sendExtra.getStringExtra("vno");

        new Handler(). postDelayed(() -> {
            Intent gotoBookSeat = new Intent(getApplicationContext() , fare.class);
            gotoBookSeat.putExtra("vno",val);
            startActivity (gotoBookSeat);
            finish();
        }, 2000);
    }
}