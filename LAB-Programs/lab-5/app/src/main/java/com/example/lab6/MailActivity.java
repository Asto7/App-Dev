package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MailActivity extends AppCompatActivity {
    EditText senderAddressET, mailSubjectET, mailBodyET;
    Button send;

    Context context;

    String senderAddress = "", mailSubject = "", mailBody = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        senderAddressET = findViewById(R.id.sender_address);
        mailSubjectET = findViewById(R.id.mail_subject);
        mailBodyET = findViewById(R.id.mail_body);
        send = findViewById(R.id.send);

        context = this;

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senderAddress = senderAddressET.getText().toString();
                mailSubject = mailSubjectET.getText().toString();
                mailBody = mailBodyET.getText().toString();
                if(senderAddress == null || senderAddress.isEmpty()){
                    Toast.makeText(context,"Please enter sender's address",Toast.LENGTH_SHORT).show();
                } else if (mailBody == null || mailBody.isEmpty()){
                    Toast.makeText(context,"Please write something in the mail",Toast.LENGTH_SHORT).show();
                } else {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("message/rfc822");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{senderAddress});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT,mailSubject);
                    emailIntent.putExtra(Intent.EXTRA_TEXT,mailBody);
                    startActivity(Intent.createChooser(emailIntent,"Choose the app to send email:"));
                }
            }
        });
    }
}