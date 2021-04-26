package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView s1,s2,s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);
        SharedPreferences sp = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        s1.setText(sp.getString("s1",""));
        s2.setText(sp.getString("s2",""));
        s3.setText(sp.getString("s3",""));
    }
}