package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button submit;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radio_group);
        submit = findViewById(R.id.btn_submit);

        context = this;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(context, "Please select atleast one option to continue!", Toast.LENGTH_SHORT).show();
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_mail) {
                    startActivity(new Intent(MainActivity.this, MailActivity.class));
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_alarm) {
                    startActivity(new Intent(MainActivity.this, AlarmActivity.class));
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_music) {
                    startActivity(new Intent(MainActivity.this, PlaySongActivity.class));
                }
            }
        });
    }
}