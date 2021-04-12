package com.example.lab_8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class DisplayAll extends AppCompatActivity {


    DatabaseHelper databaseHelper;
    List<Product> all_products;
    private RecyclerView recyclerViewProducts;
    private List<Product> productList;
    private Adapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);


        databaseHelper = new DatabaseHelper(this);
        all_products = databaseHelper.getAllProduct();

        initViews();
        initObjects();

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

    private void initViews() {
        recyclerViewProducts = findViewById(R.id.recycle);
    }

    private void initObjects() {
        productList = new ArrayList<>();
        productAdapter = new Adapter(productList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProducts.setLayoutManager(mLayoutManager);
        recyclerViewProducts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProducts.setHasFixedSize(true);
        recyclerViewProducts.setAdapter(productAdapter);
        getDataFromSQLite();
    }

    private void getDataFromSQLite() {
        productList.clear();
        productList.addAll(databaseHelper.getAllProduct());
        productAdapter.notifyDataSetChanged();
    }


}