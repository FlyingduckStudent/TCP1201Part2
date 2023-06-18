package com.example.goboom;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GoBoom.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your database tables
        db.execSQL("CREATE TABLE Game_Data (id INTEGER PRIMARY KEY, players TEXT,  deck TEXT, scores TEXT, trick int)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
        // This method will be called when DATABASE_VERSION is increased

        db.execSQL("DROP TABLE IF EXISTS Game_Data");
        onCreate(db);
    }

    public boolean AddData(String playerjson, String deckjson, String scoresjson, int TrickCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("players", playerjson);
        contentValues.put("deck", deckjson);
        contentValues.put("scores", scoresjson);
        contentValues.put("trick", TrickCount);

        Log.d("Database Helper : ", "Data Added");

        long result = db.insert("Game_Data", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//    public boolean LoadData(){
//
//
//    }
}
