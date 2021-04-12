package com.example.lab_8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DisplayOne extends AppCompatActivity {
    EditText searchText;
    LinearLayout parDiv;
    DatabaseHelper databaseHelper;

    EditText name, mrp, selling_price;
    String p_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_one);


        searchText = findViewById(R.id.search_product_id);
        parDiv = findViewById(R.id.parrentDiv);

        name = findViewById(R.id.name);
        mrp = findViewById(R.id.mrp);
        selling_price = findViewById(R.id.selling_price);

        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home: {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            }

            case R.id.show_all: {
                Intent i = new Intent(this, DisplayAll.class);
                startActivity(i);
                break;
            }

            case R.id.show_one: {
                Intent i = new Intent(this, DisplayOne.class);
                startActivity(i);
                break;
            }

            case R.id.edit_one: {
                Intent i = new Intent(this, UpdateOne.class);
                startActivity(i);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void searchProduct(View view) {
        parDiv.setVisibility(View.INVISIBLE);

        String SEARCH_ID = searchText.getText().toString().trim();
        Product product = databaseHelper.retProduct(SEARCH_ID);

        p_id = SEARCH_ID;

        if (product != null) {
            parDiv.setVisibility(View.VISIBLE);

            name.setText(product.getName());
            mrp.setText(product.getMrp());
            selling_price.setText(product.getPrice());

        } else {
            Toast.makeText(this, "No product Exists with this id!", Toast.LENGTH_SHORT).show();
        }
    }


}