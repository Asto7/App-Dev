package com.example.modelexam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MultiActivity extends AppCompatActivity {


    Random rnd = new Random();
    TextView p1ScoreText, p2ScoreText, turnsView;
    int p1score = 0, p2score = 0;
    int turn = 1;
    int numOfTurns;
    boolean singlePlayerGame = false;

    Button rollBtn, rollBtn2, resetBtn;
    ImageView diePic, diePic2;

    String pl1Name, pl2Name;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        numOfTurns = 6;

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        pl1Name = extras.getString("pl1Name");
        pl2Name = extras.getString("pl2Name");


        singlePlayerGame = false;
        singlePlayerGame = pl2Name.equals("Computer");

        Toast.makeText(this, "Game Started " + pl1Name + " chance", 0).show();

        diePic = (ImageView) findViewById(R.id.imgDice);
        diePic2 = (ImageView) findViewById(R.id.img2Dice);

        p1ScoreText = (TextView) findViewById(R.id.tvp1);
        p2ScoreText = (TextView) findViewById(R.id.tvp2);


        rollBtn = (Button) findViewById((R.id.btnRoll));
        rollBtn2 = (Button) findViewById((R.id.btnRoll2));

        turnsView = findViewById((R.id.turns));


        rollBtn.setText(pl1Name + " TURN");
        rollBtn2.setText(pl2Name + " TURN");

        rollBtn2.setEnabled(false);


        resetAll();


        resetBtn = (Button) findViewById(R.id.btnReset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home: {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                return true;
            }

            case R.id.action_help: {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=Dice+Games"));
                startActivity(intent);
                return true;
            }
            case R.id.action_refresh: {
                resetAll();
                return true;
            }
            case R.id.action_exit: {
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetAll() {
        p1score = 0;
        p2score = 0;
        numOfTurns = 6;
        turnsView.setText("Turns Left = " + ((numOfTurns + 1) / 2));
        turn = 1;

        p1ScoreText.setText(pl1Name + " = 0");
        p2ScoreText.setText(pl2Name + " = 0");

        diePic.setImageResource(R.drawable.fulldice);
        diePic2.setImageResource(R.drawable.fulldice);

        rollBtn.setText(pl1Name + " Turn");
        rollBtn2.setText(pl2Name + " Turn");

        rollBtn.setEnabled(true);
        rollBtn2.setEnabled(false);
    }

    private void diceRotate() {
        int num = rnd.nextInt(6) + 1;


        Animation rolling = AnimationUtils.loadAnimation(this, R.anim.rotate);
        diePic.setImageResource(R.drawable.fulldice);


        rolling.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                diePic.setImageResource(R.drawable.fulldice);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                numOfTurns -= 1;
                turnsView.setText("Turns Left = " + ((numOfTurns + 1) / 2));

                switch (num) {
                    case 1:
                        diePic.setImageResource(R.drawable.die_1);
                        break;
                    case 2:
                        diePic.setImageResource(R.drawable.die_2);
                        break;
                    case 3:
                        diePic.setImageResource(R.drawable.die_3);
                        break;
                    case 4:
                        diePic.setImageResource(R.drawable.die_4);
                        break;
                    case 5:
                        diePic.setImageResource(R.drawable.die_5);
                        break;
                    case 6:
                        diePic.setImageResource(R.drawable.die_6);
                        break;
                }

                if (turn == 1) {

                    p1score += num;
                    String p1text = pl1Name + " = " + Integer.toString(p1score);
                    p1ScoreText.setText(p1text);
                    turn *= -1;

                    if (numOfTurns <= 0) {
                        Toast.makeText(MultiActivity.this, pl1Name + " Won with score " + p1score, Toast.LENGTH_SHORT).show();
                        turnsView.setText((pl1Name + " Won with score " + p1score));
                        rollBtn.setText("Press Reset");
                        rollBtn.setEnabled(false);
                        rollBtn2.setText("Press Reset");
                        rollBtn2.setEnabled(false);

                    }


                } else if (turn == -1) {

                    p2score += num;
                    String p2text = pl2Name + " = " + Integer.toString(p2score);
                    p2ScoreText.setText(p2text);
                    turn *= -1;

                    rollBtn.setEnabled(true);

                    if (numOfTurns <= 0) {
                        Toast.makeText(MultiActivity.this, pl2Name + " Won with score " + p2score, Toast.LENGTH_SHORT).show();
                        turnsView.setText((pl1Name + " Won with score " + p1score));
                        rollBtn.setText("Press Reset");
                        rollBtn.setEnabled(false);
                        rollBtn2.setText("Press Reset");
                        rollBtn2.setEnabled(false);

                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        diePic.startAnimation((rolling));
    }


    private void diceRotate2() {
        int num = rnd.nextInt(6) + 1;

        Animation rolling = AnimationUtils.loadAnimation(this, R.anim.rotate);
        diePic2.setImageResource(R.drawable.fulldice);

        rolling.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                diePic2.setImageResource(R.drawable.fulldice);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                numOfTurns -= 1;
                turnsView.setText("Turns Left = " + ((numOfTurns + 1) / 2));

                switch (num) {
                    case 1:
                        diePic2.setImageResource(R.drawable.die_1);
                        break;
                    case 2:
                        diePic2.setImageResource(R.drawable.die_2);
                        break;
                    case 3:
                        diePic2.setImageResource(R.drawable.die_3);
                        break;
                    case 4:
                        diePic2.setImageResource(R.drawable.die_4);
                        break;
                    case 5:
                        diePic2.setImageResource(R.drawable.die_5);
                        break;
                    case 6:
                        diePic2.setImageResource(R.drawable.die_6);
                        break;
                }

                if (turn == 1) {

                    p1score += num;
                    String p1text = pl1Name + " = " + Integer.toString(p1score);
                    p1ScoreText.setText(p1text);
                    turn *= -1;


                    if (numOfTurns <= 0) {
                        Toast.makeText(MultiActivity.this, pl1Name + " Won with score " + p1score, Toast.LENGTH_SHORT).show();
                        turnsView.setText((pl1Name + " Won with score " + p1score));

                        rollBtn.setText("Press Reset");
                        rollBtn.setEnabled(false);
                        rollBtn2.setText("Press Reset");
                        rollBtn2.setEnabled(false);

                    }


                } else if (turn == -1) {
                    p2score += num;
                    String p2text = pl2Name + " = " + Integer.toString(p2score);
                    p2ScoreText.setText(p2text);
                    turn *= -1;


                    if (numOfTurns <= 0) {
                        Toast.makeText(MultiActivity.this, pl2Name + " Won with score " + p2score, Toast.LENGTH_SHORT).show();
                        turnsView.setText((pl1Name + " Won with score " + p1score));
                        rollBtn.setText("Press Reset");
                        rollBtn.setEnabled(false);
                        rollBtn2.setText("Press Reset");
                        rollBtn2.setEnabled(false);
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        diePic2.startAnimation((rolling));


    }

    public void btnOnlick(View view) {
        rollBtn.setEnabled(false);
        rollBtn2.setEnabled(false);

        diceRotate();

        if (numOfTurns > 0) {
            rollBtn2.setEnabled(true);
        }
    }

    public void btnOnlick2(View view) {
        rollBtn.setEnabled(false);
        rollBtn2.setEnabled(false);


        diceRotate2();

        if (numOfTurns > 0) {
            rollBtn.setEnabled(true);
        }
    }
}