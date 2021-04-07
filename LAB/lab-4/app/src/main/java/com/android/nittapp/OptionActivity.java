package com.android.nittapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.PKIXRevocationChecker;

public class OptionActivity extends AppCompatActivity {

    Button goBtn, backBtn;
    RadioGroup rg;
    String roll_number, name, query;
    TextView tv_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        Bundle bundle = getIntent().getExtras();

        roll_number = bundle.getString("roll_number");
        name = bundle.getString("user_name");

        Toast.makeText(getApplicationContext(), "Welcome " + name + ", Launching activity - 2", Toast.LENGTH_SHORT).show();

        tv_head = findViewById(R.id.tvHead);
        rg = findViewById(R.id.rgServices);
        goBtn = (Button) findViewById(R.id.btnGo);
        backBtn = (Button) findViewById(R.id.btnBack);

        tv_head.setText("Available Services for " + roll_number);

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDesiredIntent();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMainActivity();
            }
        });
    }

    private void OpenMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("roll_num", roll_number);
        i.putExtra("user_name", name);
        startActivity(i);
    }

    private void OpenDesiredIntent() {
        int id = rg.getCheckedRadioButtonId();
        EditText et = (findViewById(R.id.inputMessage));
        query = et.getText().toString();

        if (query.length() <= 0) {
            Toast.makeText(this, "Type atleast 1 character in message body!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        switch (id) {
            case R.id.rbCall:
                try {
                    String number = "";
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, query));
                    i.putExtra("sms_body", query);
                    startActivity(i);
                } catch (SecurityException s) {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT)
                            .show();
                }
                break;


            case R.id.rbYt:

                try {
                    Intent sendMail = new Intent(Intent.ACTION_SEND);
                    sendMail.putExtra(Intent.EXTRA_TEXT, query);
                    sendMail.setType("message/rfc822");
                    startActivity(sendMail);

                } catch (SecurityException s) {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            case R.id.rbWeb:

                try {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, query); // query contains search string
                    startActivity(intent);
                } catch (SecurityException s) {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            default:
                Toast.makeText(this, "Select Radio Button to proceed!", Toast.LENGTH_SHORT)
                        .show();
        }
    }
}