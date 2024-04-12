package com.example.parking_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class park extends AppCompatActivity {

    TextView vno;
    Spinner spinnerHour , spinnerMinute;
    Button calfare ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        calfare = findViewById(R.id.calFare);
        spinnerHour = findViewById(R.id.spinnerRow);
        spinnerMinute = findViewById(R.id.spinnerMin);
        vno = findViewById(R.id.vno);

        calfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSplash1 = new Intent(getApplicationContext() , splash1.class);
                gotoSplash1.putExtra("vno",vno.getText());
                startActivity(gotoSplash1);
            }
        });

        spinnerMinute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedHour = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected
            }
        });
        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedHour = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected
            }
        });

        ArrayAdapter<CharSequence> Houradapter = ArrayAdapter.createFromResource(this, R.array.Hours, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        Houradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHour.setAdapter(Houradapter);

        ArrayAdapter<CharSequence> Minuteadapter = ArrayAdapter.createFromResource(this, R.array.minute, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        Minuteadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinute.setAdapter(Minuteadapter);


    }
}