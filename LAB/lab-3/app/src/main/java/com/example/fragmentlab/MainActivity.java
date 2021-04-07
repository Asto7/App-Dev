package com.example.fragmentlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String rollNum;
    Fragment1 sampleFragment1;
    Fragment2 sampleFragment2;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void addFragment() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        sampleFragment1 = new Fragment1(rollNum);
        fragmentTransaction.replace(R.id.fragmentContainer, sampleFragment1);
        fragmentTransaction.commit();
        Toast.makeText(MainActivity.this, "Instantiating Fragment - 1", Toast.LENGTH_LONG).show();
    }

    private void addFragment2(String Data) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        sampleFragment2 = new Fragment2(Data);
        fragmentTransaction.replace(R.id.fragmentContainer, sampleFragment2);
        fragmentTransaction.commit();
        Toast.makeText(MainActivity.this, "Instantiating Fragment - 2", Toast.LENGTH_LONG).show();
    }

    public void handleMainSubmit(View view) {
        EditText num = (EditText) findViewById(R.id.rollNumber);
        rollNum = (num.getText().toString().trim());

        if (rollNum.length() == 9) {
            addFragment();
        } else {
            Toast.makeText(MainActivity.this, "Enter Valid Roll Number of Size - 9", Toast.LENGTH_LONG).show();
        }
    }

    public void Fragment1Submit(View view) {
        CheckBox bt1 = sampleFragment1.getView().findViewById(R.id.checkBox);
        CheckBox bt2 = sampleFragment1.getView().findViewById(R.id.checkBox2);
        CheckBox bt3 = sampleFragment1.getView().findViewById(R.id.checkBox3);
        CheckBox bt4 = sampleFragment1.getView().findViewById(R.id.checkBox4);
        CheckBox bt5 = sampleFragment1.getView().findViewById(R.id.checkBox5);
        String result = "Roll Number - " + rollNum;

        if (!bt1.isChecked() && !bt2.isChecked() && !bt3.isChecked() && !bt4.isChecked() && !bt5.isChecked()) {
            Toast.makeText(MainActivity.this, "Select atleast 1 Course", Toast.LENGTH_LONG).show();
        } else {

            if (bt1.isChecked())
                result += "\n" + "Registered In App Dev Course";

            if (bt2.isChecked())
                result += "\n" + "Registered In Machine Learning Course";

            if (bt3.isChecked())
                result += "\n" + "Registered In Artificial Intelligence Course";

            if (bt4.isChecked())
                result += "\n" + "Registered In Data Mining Course";

            if (bt5.isChecked())
                result += "\n" + "Registered In Robotics Course";

            addFragment2(result);
        }
    }
}