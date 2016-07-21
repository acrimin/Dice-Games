package com.example.acrimin.dicegames;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class TenThousand extends AppCompatActivity {
    private LinearLayout table;
    private double scale;
    private ArrayList<LinearLayout> diceRows;
    private GameManager gameManager;
    private LinearLayout sentTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_thousand);

        table = (LinearLayout) findViewById(R.id.diceTable);
        sentTable = (LinearLayout) findViewById(R.id.sentTable);
        scale = getResources().getDisplayMetrics().density;

        diceRows = new ArrayList<>();

        gameManager = new GameManager(0, 2, getApplicationContext());
        setTable();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        gameManager.save();
    }

    private int getPixels(int dp) {
        return (int) (dp * scale + 0.5f);
    }

    private void setTable() {
        table.removeAllViewsInLayout();
        diceRows.clear();


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        int dicePerRow = (int) Math.floor(dpWidth/80);
        int rows = (int) Math.ceil((float)gameManager.getTableDiceCount() / dicePerRow);

        int d = 0;
        for (int r = 0; r < rows; r++) {
            LinearLayout diceRow = new LinearLayout(getApplicationContext());
            diceRow.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1f
            );
            diceRow.setLayoutParams(layoutParams);
            diceRow.setGravity(Gravity.CENTER);

            dicePerRow = (int) Math.ceil((float) (gameManager.getTableDiceCount() - d) / (rows - r));
            for (int i = 0; i < dicePerRow; i++) {
                diceRow.addView(gameManager.getTableDice(d).getImageView());
                d++;
            }
            table.addView(diceRow);
        }
        diceRows.clear();
    }

    public void rollPress(View view) {
        gameManager.roll();
    }

    public void sendPress(View view) {
        gameManager.send();
    }

}
