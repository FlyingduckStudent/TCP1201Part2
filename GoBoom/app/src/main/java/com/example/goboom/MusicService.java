package com.example.goboom;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class MusicService extends Service {
    private MediaPlayer mMediaPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {

        mMediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.song);
        mMediaPlayer.setLooping(false);
        }


    public void onStart(Intent intent, int startid) {
        mMediaPlayer.start();
        }


    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
    }
}

