package com.example.hikerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ViewLogActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        List<LogModel> logModels = dbHelper.getLogList();

        if (logModels.size() > 0){
            LogAdapter logAdapter = new LogAdapter(logModels, ViewLogActivity.this);
            recyclerView.setAdapter(logAdapter);
        } else {
            Toast.makeText(this, "no log in data", Toast.LENGTH_SHORT).show();
        }
    }
}