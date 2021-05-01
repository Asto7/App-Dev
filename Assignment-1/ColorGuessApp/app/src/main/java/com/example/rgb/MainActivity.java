package com.example.rgb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView title;
    Button level;
    Button[] C;

    int[] cellIndex = {(R.id.c1b), (R.id.c2b), (R.id.c3b),
            (R.id.c4b), (R.id.c5b), (R.id.c6b),
            (R.id.c7b), (R.id.c8b), (R.id.c9b)};

    int pickedcolor;
    List<RGB> cardcolors;
    boolean isEasy = true;

    LinearLayout row3;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        row3 = findViewById(R.id.row3);

        C = new Button[9];
        for (int i = 0; i < 9; i++) {
            C[i] = findViewById(cellIndex[i]);
        }

        level = findViewById(R.id.level);
        title = findViewById(R.id.rgbcode);

        cardcolors = new ArrayList<>();

        row3.setVisibility(View.INVISIBLE);

        setColorBoard(6); // It'll start with easy mode

        for (int i = 0; i < 9; i++) {
            C[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currId = view.getId();

                    for (int j = 0; j < 9; j++) {
                        if (currId == cellIndex[j]) {
                            currId = j;
                            break;
                        }
                    }

                    if (cardcolors.get(currId).getR() == cardcolors.get(pickedcolor).getR()
                            && cardcolors.get(currId).getG() == cardcolors.get(pickedcolor).getG()
                            && cardcolors.get(currId).getB() == cardcolors.get(pickedcolor).getB()) {

                        Toast.makeText(MainActivity.this, "\tCongrats You won the game!\nClick on Play Again to restart :)", Toast.LENGTH_LONG).show();

                        for (int j = 0; j < 9; j++) {
                            if (j != currId) {
                                C[j].setVisibility(View.GONE);
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Choose another one", Toast.LENGTH_SHORT).show();
                        C[currId].setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    void setColorBoard(int numOfCells) {
        cardcolors.clear();

        for (int i = 0; i < numOfCells; i++) {
            RGB rgb = new RGB();
            cardcolors.add(rgb);
        }

        pickedcolor = (int) Math.floor(Math.random() * cardcolors.size());
        title.setText("Guess this Color \n" + cardcolors.get(pickedcolor).toString());

        for (int i = 0; i < numOfCells; i++) {
            C[i].setVisibility(View.VISIBLE);
            C[i].setBackgroundColor(Color.rgb((int) cardcolors.get(i).getR(), (int) cardcolors.get(i).getG(), (int) cardcolors.get(i).getB()));
        }
    }

    public void chooseLevel(View view) {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        dialog.setContentView(R.layout.dialog_items);

        final RadioButton rb2 = dialog.findViewById(R.id.cb2);
        final RadioButton rb3 = dialog.findViewById(R.id.cb3);

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                isEasy = true;
                row3.setVisibility(View.INVISIBLE);
                setColorBoard(6);
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                isEasy = false;
                row3.setVisibility(View.VISIBLE);
                setColorBoard(9);
            }
        });

        dialog.setTitle("Select a level");
        dialog.show();
    }

    public void restartGame(View view) {
        if (isEasy == false)
            setColorBoard(9);
        else
            setColorBoard(6);
    }
}