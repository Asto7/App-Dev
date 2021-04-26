 package com.example.lab9;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView t1, t2, t3, t4, t5, t6;
    Button c1, c2, c3, c4, c5, c6, submit;
    String s[] = {"Gray", "Red", "LightRed", "DarkBlue", "SkyBlue", "LightGreen", "DarkGreen", "LimeYellow", "DarkYellow", "Orange", "GreenBlue", "Pink", "Violet", "Purple"};
    int score = 0;
    String a1 = "NP", a2 = "NP", a3 = "NP";
    NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        submit = findViewById(R.id.submit);

        shuffleArray(14);
        t1.setText(s[0]);
        t2.setText(s[1]);
        t3.setText(s[2]);
        t4.setText(s[3]);
        t5.setText(s[4]);
        t6.setText(s[5]);

        shuffleArray(6);
        c1.setBackgroundColor(getResources().getColor(colour(s[0])));
        c2.setBackgroundColor(getResources().getColor(colour(s[1])));
        c3.setBackgroundColor(getResources().getColor(colour(s[2])));
        c4.setBackgroundColor(getResources().getColor(colour(s[3])));
        c5.setBackgroundColor(getResources().getColor(colour(s[4])));
        c6.setBackgroundColor(getResources().getColor(colour(s[5])));

        t1.setOnTouchListener(new ChoiceTouchListener());
        t2.setOnTouchListener(new ChoiceTouchListener());
        t3.setOnTouchListener(new ChoiceTouchListener());
        t4.setOnTouchListener(new ChoiceTouchListener());
        t5.setOnTouchListener(new ChoiceTouchListener());
        t6.setOnTouchListener(new ChoiceTouchListener());

        c1.setOnDragListener(new ChoiceDragListener());
        c2.setOnDragListener(new ChoiceDragListener());
        c3.setOnDragListener(new ChoiceDragListener());
        c4.setOnDragListener(new ChoiceDragListener());
        c5.setOnDragListener(new ChoiceDragListener());
        c6.setOnDragListener(new ChoiceDragListener());

        notificationManager = NotificationManagerCompat.from(this);

        SharedPreferences sp = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        if (!sp.contains("s1")) {
            ed = sp.edit();
            ed.putString("s1", a1);
            ed.putString("s2", a2);
            ed.putString("s3", a3);
            ed.commit();
        }

        SharedPreferences.Editor finalEd = ed;

        submit.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (c1.getText().toString().matches("") || c2.getText().toString().matches("") || c3.getText().toString().matches("") || c4.getText().toString().matches("") || c5.getText().toString().matches("") || c6.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Please fill all the options", Toast.LENGTH_SHORT).show();
                    return;
                }
                score = 0;
                if (c1.getText().toString().matches(s[0]))
                    score++;
                if (c2.getText().toString().matches(s[1]))
                    score++;
                if (c3.getText().toString().matches(s[2]))
                    score++;
                if (c4.getText().toString().matches(s[3]))
                    score++;
                if (c5.getText().toString().matches(s[4]))
                    score++;
                if (c6.getText().toString().matches(s[5]))
                    score++;
                a1 = sp.getString("s1", "");
                a2 = sp.getString("s2", "");
                a3 = sp.getString("s3", "");
                a3 = a2;
                a2 = a1;
                a1 = String.valueOf(score);
                finalEd.putString("s1", a1);
                finalEd.putString("s2", a2);
                finalEd.putString("s3", a3);
                finalEd.apply();
                sendNotif();
                newGame();
            }
        });
    }


    public int colour(String s) {
        if (s.matches("Gray"))
            return R.color.Gray;
        if (s.matches("Red"))
            return R.color.Red;
        if (s.matches("LightRed"))
            return R.color.LightRed;
        if (s.matches("DarkBlue"))
            return R.color.DarkBlue;
        if (s.matches("SkyBlue"))
            return R.color.SkyBlue;
        if (s.matches("LightGreen"))
            return R.color.LightGreen;
        if (s.matches("DarkGreen"))
            return R.color.DarkGreen;
        if (s.matches("LimeYellow"))
            return R.color.LimeYellow;
        if (s.matches("DarkYellow"))
            return R.color.DarkYellow;
        if (s.matches("Orange"))
            return R.color.Orange;
        if (s.matches("GreenBlue"))
            return R.color.GreenBlue;
        if (s.matches("Pink"))
            return R.color.Pink;
        if (s.matches("Violet"))
            return R.color.Violet;
        if (s.matches("Purple"))
            return R.color.Purple;
        return 0;
    }

    private void swap(int i, int change) {
        String helper = s[i];
        s[i] = s[change];
        s[change] = helper;
    }

    public void shuffleArray(int n) {
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(i, change);
        }
    }

    public void refresh() {
        c1.setText("");
        c2.setText("");
        c3.setText("");
        c4.setText("");
        c5.setText("");
        c6.setText("");
    }

    public void newGame() {
        refresh();

        shuffleArray(14);
        t1.setText(s[0]);
        t2.setText(s[1]);
        t3.setText(s[2]);
        t4.setText(s[3]);
        t5.setText(s[4]);
        t6.setText(s[5]);

        shuffleArray(6);
        c1.setBackgroundColor(getResources().getColor(colour(s[0])));
        c2.setBackgroundColor(getResources().getColor(colour(s[1])));
        c3.setBackgroundColor(getResources().getColor(colour(s[2])));
        c4.setBackgroundColor(getResources().getColor(colour(s[3])));
        c5.setBackgroundColor(getResources().getColor(colour(s[4])));
        c6.setBackgroundColor(getResources().getColor(colour(s[5])));
    }
    
    public void sendNotif() {
        String title = "You Lost";
        if (score == 6)
            title = "You Won";
        String message = "Your score is " + score + "/6";
        Intent i = new Intent(this, ScoreActivity.class);

        PendingIntent pi = PendingIntent.getActivity(this, 1, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_refresh)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1, notification);
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if ((event.getAction() == MotionEvent.ACTION_DOWN) && ((TextView) v).getText().toString() != null) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            } else
                return false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.newGame:
                Toast.makeText(this, "New Game", Toast.LENGTH_SHORT).show();
                newGame();
                return true;
            case R.id.refresh:
                Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
                refresh();
                return true;
            case R.id.exit:
                Toast.makeText(this, "Exiting", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public final class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    break;

                case DragEvent.ACTION_DROP:
                    TextView t = (TextView) event.getLocalState();
                    String s = t.getText().toString();
                    ((Button) v).setText(s);
                    break;
            }
            return true;
        }
    }
}