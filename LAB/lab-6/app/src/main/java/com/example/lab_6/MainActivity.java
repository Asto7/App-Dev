package com.example.lab_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    EditText name, email, mobileNumber;
    TextView nameOut, emailOut, mobileNumberOut;
    Button button, button2;
    SharedPreferences sp;

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobileNumber = findViewById(R.id.mb);

        nameOut = findViewById(R.id.nameOutput);
        emailOut = findViewById(R.id.emailOutput);
        mobileNumberOut = findViewById(R.id.mobileNumberOutput);

        button = findViewById(R.id.submit);
        button2 = findViewById(R.id.show);

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().trim().length() <= 2) {
                    Toast.makeText(MainActivity.this, "Name Should have atleast 3 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidMail(email.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter Valid Email!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!isValidMobile(mobileNumber.getText().toString()) || mobileNumber.getText().toString().trim().length() <= 6 || mobileNumber.getText().toString().trim().length() > 13) {
                    Toast.makeText(MainActivity.this, "Enter Valid Number of length between 7 digit to 13 digit!", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sp.edit();

                editor.putString("name", name.getText().toString());
                editor.putString("email", email.getText().toString());
                editor.putString("mobileNumber", mobileNumber.getText().toString());
                editor.commit();

                Toast.makeText(MainActivity.this, "Information Saved", Toast.LENGTH_SHORT).show();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sp.getString("name", "").trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "First Insert Data!", Toast.LENGTH_SHORT).show();
                    return;
                }
                nameOut.setText("Name: " + sp.getString("name", ""));
                emailOut.setText("Email: " + sp.getString("email", ""));
                mobileNumberOut.setText("Number: " + sp.getString("mobileNumber", ""));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setp: {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                break;
            }
            case R.id.ctrl: {
                Intent i = new Intent(this, Controls.class);
                startActivity(i);
                break;
            }
            case R.id.hlp: {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://support.google.com/android/?hl=en#topic=7313011"));
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