package com.example.lab_1_helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitAdd(View view) {
        try{
            TextView warn = (TextView) findViewById(R.id.warn);
            TextView success = (TextView) findViewById(R.id.success);

            warn.setText("");
            success.setText("");

            EditText num1 = (EditText) findViewById(R.id.editTextNumber);
            EditText num2 = (EditText) findViewById(R.id.editTextNumber2);

            double a = Double.parseDouble(num1.getText().toString());
            double b = Double.parseDouble((num2.getText().toString()));
//            Log.d("kira", String.valueOf(a) );

            success.setText(String.valueOf(a + b));
        }

        catch(Exception e){
            TextView warn = (TextView) findViewById(R.id.warn);
            warn.setText("*Please Enter both values to get the sum");
        }
     }


}