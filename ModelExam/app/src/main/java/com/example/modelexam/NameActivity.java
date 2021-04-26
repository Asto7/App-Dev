package com.example.modelexam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {

    LinearLayout parentId;
    String type, pl1Name, pl2Name;
    EditText player1, player2;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        type = extras.getString("mode");

        Toast.makeText(this, type + " player mode", 0).show();

        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        parentId = findViewById(R.id.parentPlayer2);

        if (type.equals("single")) {
            parentId.setVisibility(View.INVISIBLE);
            pl2Name = "Computer";
        }
    }

    public void Submit(View view) {
        pl1Name = player1.getText().toString();
        String temp = player2.getText().toString();

        if (temp.length() > 0) {
            pl2Name = temp;
        }

        if (pl1Name.length() <= 3) {

            Toast.makeText(this, "Player-1 name should be atleast 4 chars long!", 0).show();
            return;
        }

        if (pl2Name.length() <= 3) {

            Toast.makeText(this, "Player-2 name should be atleast 4 chars long!", 0).show();
            return;
        }

        if (pl1Name.equals(pl2Name)) {
            Toast.makeText(this, "Player-1 name should be differnet than Player-2 name!!!", 0).show();
            return;

        }

        if (!pl2Name.equals("Computer")) {
            Intent i = new Intent(this, MultiActivity.class);
            i.putExtra("pl1Name", pl1Name);
            i.putExtra("pl2Name", pl2Name);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return;
        }

        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("pl1Name", pl1Name);
        i.putExtra("pl2Name", pl2Name);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}