package com.example.lab_8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UpdateOne extends AppCompatActivity {

    EditText searchText;
    LinearLayout parDiv;
    DatabaseHelper databaseHelper;

    EditText name, mrp, selling_price;
    Button update;
    String p_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_one);

        searchText = findViewById(R.id.search_product_id);
        parDiv = findViewById(R.id.parrentDiv);

        name = findViewById(R.id.name);
        mrp = findViewById(R.id.mrp);
        selling_price = findViewById(R.id.selling_price);
        update = findViewById(R.id.update);

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
            Toast.makeText(this, "No product with this id Exists!", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {

        String NAME = name.getText().toString().trim();
        String P_ID = p_id;
        String MRP = mrp.getText().toString().trim();
        String SELLING_PRICE = selling_price.getText().toString().trim();

        ValidateInput checker = new ValidateInput(P_ID, NAME, SELLING_PRICE, MRP);

        String output = checker.valid();

        if (output.length() > 0) {
            Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
            return;
        }

        databaseHelper.updateProduct(P_ID, NAME, MRP, SELLING_PRICE);

        Toast.makeText(this, "Successfully Updated!", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(this, NewAppWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), NewAppWidget.class));

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);

        Intent i = new Intent(this, DisplayAll.class);
        startActivity(i);
    }
}