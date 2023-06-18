package com.example.goboom;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

    }

    public void onStart(View view) {
        Intent intent = new Intent(MainActivity.this, Start_Page2.class);
        Bundle bundle = new Bundle();
        bundle.putString("activity","started");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void Music(View view) {
        Intent intent = new Intent(MainActivity.this, Music.class);
        startActivity(intent);
    }

    public void onRules(View view) {
        Intent intent = new Intent(MainActivity.this, Rules.class);
        startActivity(intent);
    }

    public void Exit(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void OnLoad(View view){
        Intent intent = new Intent(MainActivity.this, Start_Page2.class);
        Bundle bundle = new Bundle();
        bundle.putString("activity","loaded");
        intent.putExtras(bundle);
        startActivity(intent);
    }

}