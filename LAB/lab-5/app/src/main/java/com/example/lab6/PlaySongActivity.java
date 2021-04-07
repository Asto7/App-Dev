package com.example.lab6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class PlaySongActivity extends AppCompatActivity {

    Button open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        open = findViewById(R.id.open_song);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filePath = "/storage/emulated/0/Download/Death Note - Ls Theme.mp3";
                File file = new File(filePath);

                try {
                    if (file.exists()) {
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse(filePath), "audio/*");
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "The desired music isn't available!", 0).show();

                        Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", 0).show();
                }
            }
        });
    }
}