package com.example.lab_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button register;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.onSubmit);
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("lab-07", MODE_PRIVATE);
    }

    public void redirectToRegister(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void onClickLogin(View view) {

        String Username, Password;
        Username = username.getText().toString().trim();
        Password = password.getText().toString().trim();

        if (!databaseHelper.checkUser(Username, Password)) {
            Toast.makeText(this, "Username or Password is wrong!", Toast.LENGTH_LONG).show();
            return;
        } else {
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("username", Username);
            myEdit.putInt("isLoggedIn", 1);
            myEdit.commit();

            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

            return;
        }
    }
}