package com.android.nittapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText rollnumber,name;
    private String user_name,roll_number;


    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Welcome to Messaging App", Toast.LENGTH_SHORT).show();

        rollnumber = findViewById(R.id.etRoll);
        name = findViewById(R.id.etName);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            rollnumber.setText(bundle.getString("roll_num"));
            name.setText(bundle.getString("user_name"));
        }

        submitBtn=(Button) findViewById(R.id.btnSubmit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityOption();
            }
        });
    }

    private void openActivityOption() {

        closeKeyboard();

        user_name = name.getText().toString();
        roll_number = rollnumber.getText().toString();

        if(roll_number.length() != 9){
            Toast.makeText(getApplicationContext(),"Enter valid Roll Number of length 9",Toast.LENGTH_SHORT).show();
            return;
        }

        if(user_name.matches("")){
            Toast.makeText(getApplicationContext(),"Enter valid name",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent(this, OptionActivity.class);
        i.putExtra("user_name", user_name);
        i.putExtra("roll_number", roll_number);
        startActivity(i);
    }



    public void closeKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(name.getWindowToken(),0);
    }

}