package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int selectTypeIndex;
    Spinner fromSpinner, toSpinner, dropdown;
    EditText fromEditText, toEditText;

    String[] items = new String[]{"Length", "Temperature", "Time", "Mass", "Angle", "Speed", "Pressure"};
    int[] itemsIndex = new int[]{R.array.units_length, R.array.units_temperature, R.array.units_time, R.array.units_mass, R.array.units_angle, R.array.units_speed, R.array.units_pressure};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromEditText = (EditText) findViewById(R.id.editText_from);
        toEditText = (EditText) findViewById(R.id.editText_to);

        fromSpinner = (Spinner) findViewById(R.id.spinner_from);
        toSpinner = (Spinner) findViewById(R.id.spinner_to);
        dropdown = (Spinner) findViewById(R.id.selectType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units_length, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter2);

        selectTypeIndex = 0;

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null)
                    ShowAppropriateOptions((int) id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void ShowAppropriateOptions(int id) {

        selectTypeIndex = id;
        toEditText.setText("");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, itemsIndex[selectTypeIndex], android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
    }

    public void convert(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null && this.getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

        String fromString = (String) fromSpinner.getSelectedItem();
        String toString = (String) toSpinner.getSelectedItem();

        String output = fromEditText.getText().toString().trim();

        if (output.length() <= 0) {
            Toast.makeText(this, "Please Enter Input!", Toast.LENGTH_SHORT).show();
            return;
        }

        double input = Double.parseDouble(fromEditText.getText().toString().trim());

        Converter converter = new Converter(fromString, toString, items[selectTypeIndex]);
        NumberFormat formatter = new DecimalFormat("###,###.############");
        double result = converter.convert(input);
        toEditText.setText(String.valueOf(formatter.format(result)));
    }
}