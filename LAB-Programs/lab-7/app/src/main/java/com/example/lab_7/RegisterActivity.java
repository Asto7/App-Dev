package com.example.lab_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    EditText name, username, email, password, confirm_password, mobile_number;
    Button register;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        mobile_number = findViewById(R.id.number);
        register = findViewById(R.id.register);
    }

    public void redirectToLogin(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void onClickRegister(View view) {

        String Name, Username, Email, Password, ConfirmPassword, MobileNumber;
        Name = name.getText().toString().trim();
        Username = username.getText().toString().trim();
        MobileNumber = mobile_number.getText().toString().trim();
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();
        ConfirmPassword = confirm_password.getText().toString().trim();

        if (Name.length() < 4) {
            Toast.makeText(this, "Name should have atleast 4 chars", Toast.LENGTH_LONG).show();
            return;
        }

        String x[] = Username.split(" ");

        if (Username.length() < 4 || x.length != 1) {
            Toast.makeText(this, "Username should have atleast 4 chars and should be 1 word!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!isValidEmail(Email)) {
            Toast.makeText(this, "Please Enter Valid Email!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!Password.equals(ConfirmPassword)) {
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }

        if (Password.length() < 4) {
            Toast.makeText(this, "Password Should be more than 4 chars", Toast.LENGTH_LONG).show();
            return;
        }

        if (MobileNumber.length() < 7) {
            Toast.makeText(this, "Mobile Number Should be atleast 7 digits", Toast.LENGTH_LONG).show();
            return;
        }

        databaseHelper = new DatabaseHelper(this);

        if (!databaseHelper.checkUserIfExists(Email, Username)) {

            User user = new User();

            user.setEmail(Email);
            user.setPhone(MobileNumber);
            user.setName(Name);
            user.setUsername(Username);
            user.setPassword(Password);

            databaseHelper.addUser(user);

            Toast.makeText(this, "Successfully Registered, Login To Proceed!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            Toast.makeText(this, "User with this Username or Email Exists!", Toast.LENGTH_LONG).show();
        }

    }
}