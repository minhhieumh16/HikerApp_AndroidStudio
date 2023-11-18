package com.example.hikerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2, edit3, edit4, edit5, edit6, edit7;
    Button save_btn, view_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit_name);
        edit2 = findViewById(R.id.edit_location);
        edit3 = findViewById(R.id.edit_date);
        edit4 = findViewById(R.id.edit_parking);
        edit5 = findViewById(R.id.edit_length);
        edit6 = findViewById(R.id.edit_level);
        edit7 = findViewById(R.id.edit_desc);

        save_btn = findViewById(R.id.btn_save);
        view_btn = findViewById(R.id.btn_view);

        //set event for save button
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringName = edit1.getText().toString();
                String stringLocation = edit2.getText().toString();
                String stringDate = edit3.getText().toString();
                String stringParking = edit4.getText().toString();
                String stringLength = edit5.getText().toString();
                String stringLevel = edit6.getText().toString();
                String stringDesc = edit7.getText().toString();

                //check validation if sting value is empty
                if (stringName.length() <=0 || stringLocation.length() <=0 || stringDate.length() <=0 || stringParking.length() <=0 || stringLength.length() <=0 || stringLevel.length() <=0) {
                    Toast.makeText(MainActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(MainActivity.this);
                    LogModel logModel = new LogModel(stringName,stringLocation,stringDate,stringParking,stringLength,stringLevel,stringDesc);
                    dbHelper.addLogHiking(logModel);
                    Toast.makeText(MainActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        //set event for view button
        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewLogActivity.class);
                startActivity(intent);
            }
        });
    }
}