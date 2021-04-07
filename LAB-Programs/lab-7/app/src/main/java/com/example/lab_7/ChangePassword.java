package com.example.lab_7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {


    EditText confirm_password, password, current_password;
    DatabaseHelper databaseHelper;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        databaseHelper = new DatabaseHelper(this);

        sh = getSharedPreferences("lab-07", MODE_PRIVATE);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        current_password = findViewById(R.id.curr_password);
    }

    public void onClickHandler(View view) {
        String Password, ConfirmPassword, CurrentPassword;
        Password = password.getText().toString().trim();
        ConfirmPassword = confirm_password.getText().toString().trim();
        CurrentPassword = current_password.getText().toString().trim();
        String Username = sh.getString("username", "");

        if (!databaseHelper.checkUser(Username, CurrentPassword)) {
            Toast.makeText(this, "Current password is wrong", Toast.LENGTH_LONG).show();
            return;
        }

        if (!Password.equals(ConfirmPassword)) {
            Toast.makeText(this, "New Password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }

        if (Password.length() < 4) {
            Toast.makeText(this, "New Password Should be more than 4 chars", Toast.LENGTH_LONG).show();
            return;
        }


        try {
            databaseHelper.updateUser(Username, Password);

            Toast.makeText(this, " Password Successfully Updated!", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        } catch (Exception e) {
            String msg = e.getMessage();
            Toast.makeText(this, "SOMETHING WENT WRONG ", Toast.LENGTH_LONG);
        }
    }
}