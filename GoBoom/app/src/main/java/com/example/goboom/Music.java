package com.example.goboom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Music extends AppCompatActivity implements View.OnClickListener {

    private Intent serviceIntent;
    private Button buttonStart,buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        buttonStart = findViewById(R.id.button1);
        buttonStop = findViewById(R.id.button2);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        serviceIntent = new Intent(getApplicationContext(),MusicService.class);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button1:
                startService(new Intent(getApplicationContext(),MusicService.class));
                break;
            case R.id.button2:
                stopService(new Intent(getApplicationContext(),MusicService.class));
                break;
        }
    }
    public void ExitMusic(View view) {
        Intent intent = new Intent(Music.this, MainActivity.class);
        startActivity(intent);
    }
}
