package com.example.acrimin.dicegames;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by acrimin on 7/20/2016.
 */
public class DataManager {

    private SQLiteDatabase db;

    public DataManager(Context context) {
        db = context.openOrCreateDatabase("DICEGAMES", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS game (" +
                "tableDice VARCHAR, " +
                "sentDice VARCHAR, " +
                "playerTurn INTEGER(2), " +
                "playerScores VARCHAR, " +
                "id INTEGER PRIMARY KEY)");
    }

    public void saveGame() {

    }

    public void loadGame(int id) {

    }

    public void deleteGame(int id) {

    }
}
