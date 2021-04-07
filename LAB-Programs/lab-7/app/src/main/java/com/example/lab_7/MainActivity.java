package com.example.lab_7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView TextOutput, TextGreet;
    SharedPreferences sh;
    DatabaseHelper databaseHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sh = getSharedPreferences("lab-07", MODE_PRIVATE);
        databaseHelper = new DatabaseHelper(this);
        int isLoggedIn = sh.getInt("isLoggedIn", 0);

        if (isLoggedIn == 1) {
            setContentView(R.layout.activity_main);
            String username = sh.getString("username", "0");


            TextGreet = findViewById(R.id.textGreet);
            TextGreet.setText("Welcome back, " + username);

            List<String> names = databaseHelper.getAllUser();

            String output = "Registered Users- \n\n\n";
            for (String name : names) {
                output = output + "\t\t• " + name + "\n\n";
            }

            TextOutput = findViewById(R.id.textOutput);
            TextOutput.setText(output);

        } else {
            Toast.makeText(this, "Please Login To Proceed!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changePassword: {
                Intent i = new Intent(this, ChangePassword.class);
                startActivity(i);
                break;
            }

            case R.id.logout: {
                sh.edit().remove("isLoggedIn").apply();
                sh.edit().remove("username").apply();

                Intent i = new Intent(this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
            }

            case R.id.ext: {
                finish();
                System.exit(0);
                break;
            }
        }


        return super.onOptionsItemSelected(item);
    }
}