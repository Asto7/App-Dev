package com.example.lab1_part2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1_part2.R;

public class MainActivity extends AppCompatActivity {

    String Selected = "add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RadioButton add = (RadioButton) findViewById(R.id.add);
        RadioButton mul = (RadioButton) findViewById(R.id.mul);
        RadioButton div = (RadioButton) findViewById(R.id.div);
        RadioButton sub = (RadioButton) findViewById(R.id.sub);

        if (add.isChecked()) {
            Selected = "add";
        } else if (mul.isChecked()) {
            Selected = "mul";
        } else if (div.isChecked()) {
            Selected = "div";
        } else if (sub.isChecked()) {
            Selected = "sub";
        }
    }

    public void submitAdd(View view) {
        try {
            TextView warn = (TextView) findViewById(R.id.warn);
            TextView success = (TextView) findViewById(R.id.success);

            warn.setText("");
            success.setText("");

            EditText num1 = (EditText) findViewById(R.id.editTextNumber);
            EditText num2 = (EditText) findViewById(R.id.editTextNumber2);

            double a = Double.parseDouble(num1.getText().toString());
            double b = Double.parseDouble((num2.getText().toString()));


            if (Selected == "add") {
                success.setText(String.valueOf(a + b));
            } else if (Selected == "sub") {
                success.setText(String.valueOf(a - b));
            } else if (Selected == "div") {
                if (b == 0) {
                    warn.setText(" In Division Second number should be non-zero!");
                } else success.setText(String.valueOf(a / b));

            } else if (Selected == "mul") {
                success.setText(String.valueOf(a * b));
            }
        } catch (Exception e) {

            TextView warn = (TextView) findViewById(R.id.warn);
            warn.setText("*Please Enter both values to get the sum");

        }
    }

    public void onClickAdd(View view) {
        Selected = "add";
        Toast.makeText(this, "Addition operation is selected!", Toast.LENGTH_SHORT).show();
    }

    public void onClickSub(View view) {
        Selected = "sub";
        Toast.makeText(this, "Subtraction operation is selected!", Toast.LENGTH_SHORT).show();
    }

    public void onClickDiv(View view) {
        Selected = "div";
        Toast.makeText(this, "Division operation is selected!", Toast.LENGTH_SHORT).show();
    }

    public void onClickMul(View view) {
        Selected = "mul";
        Toast.makeText(this, "Multiplication operation is selected!", Toast.LENGTH_SHORT).show();
    }

}