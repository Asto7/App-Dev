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
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, p_id, mrp, selling_price;
    Button add;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        p_id = findViewById(R.id.id);
        mrp = findViewById(R.id.mrp);
        selling_price = findViewById(R.id.selling_price);
        add = findViewById(R.id.add);

        databaseHelper = new DatabaseHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        menu.findItem(R.id.home).setVisible(false);
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


    public void onAddProduct(View view) {

        Product product = new Product();

        String NAME = name.getText().toString().trim();
        String P_ID = p_id.getText().toString().trim();
        String MRP = mrp.getText().toString().trim();
        String SELLING_PRICE = selling_price.getText().toString().trim();

        product.setId(P_ID);
        product.setName(NAME);
        product.setMrp(MRP);
        product.setPrice(SELLING_PRICE);

        ValidateInput checker = new ValidateInput(P_ID, NAME, SELLING_PRICE, MRP);

        String output = checker.valid();

        if (output.length() > 0) {
            Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
            return;
        }


        if (!databaseHelper.checkProduct(P_ID)) {
            databaseHelper.addProduct(product);

            name.setText("");
            p_id.setText("");
            mrp.setText("");
            selling_price.setText("");

            Toast.makeText(this, "Successfully added the Product!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, NewAppWidget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

            int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), NewAppWidget.class));

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
            sendBroadcast(intent);

            Intent i = new Intent(this, DisplayAll.class);
            startActivity(i);

            return;

        } else {
            Toast.makeText(this, "Product with Same Id Exists", Toast.LENGTH_SHORT).show();
        }


    }
}