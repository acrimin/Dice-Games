package com.example.acrimin.dicegames;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.acrimin.dicegames.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by acrimin on 7/14/2016.
 */
public class Table {
    private ArrayList<Dice> dice;
    private ArrayList<LinearLayout> diceRows;
    private LinearLayout linearLayoutTable;
    private Context context;
    private float scale;
    private SharedPreferences sharedPreferences;
    private Random random;

    public Table(int diceCount, LinearLayout linearLayoutTable, Context context) {
        this.linearLayoutTable = linearLayoutTable;
        this.scale = context.getResources().getDisplayMetrics().density;
        this.context = context;

        sharedPreferences = context.getSharedPreferences("AppPreferences", Activity.MODE_PRIVATE);

        diceRows = new ArrayList<>();
        dice = new ArrayList<>();
        random = new Random();

        if (!load())
            addDice(diceCount);
    }

    public void mAddDice(int count) {
        addDice(count);
    }

    private void addDice(int count) {
        for (int i = 0; i < count; i++) {
            dice.add(new Dice(context));
        }

        setTable();
    }

    public void save() {
        mSave();
    }

    private void mSave() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("diceCount", dice.size());

        for (int i = 0; i < dice.size(); i++) {
            editor.putInt("dice" + i, dice.get(i).getSide());
        }
        editor.commit();
    }

    private boolean load() {
        dice.clear();
        int diceCount = sharedPreferences.getInt("diceCount", -1);
        if (diceCount == -1)
            return false;

        for (int i = 0; i < diceCount; i++) {
            int d = sharedPreferences.getInt("dice" + i, -1);
            dice.add(new Dice(context, d));
        }

        setTable();
        return true;
    }

    private int getPixels(int dp) {
        return (int) (dp * scale + 0.5f);
    }

    private void setTable() {
        linearLayoutTable.removeAllViews();
        diceRows.clear();

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        int dicePerRow = (int) Math.floor(dpWidth/80);
        int rows = (int) Math.ceil((float)dice.size()/dicePerRow);

        int d = 0;
        for (int r = 0; r < rows; r++) {
            LinearLayout diceRow = new LinearLayout(context);
            diceRow.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    getPixels(0),
                    1f
            );
            diceRow.setLayoutParams(layoutParams);
            diceRow.setGravity(Gravity.CENTER);

            dicePerRow = (int) Math.ceil((float) (dice.size() - d) / (rows - r));
            for (int i = 0; i < dicePerRow; i++) {
                diceRow.addView(dice.get(d).getImageView());
                d++;
            }
            linearLayoutTable.addView(diceRow);
        }
        diceRows.clear();
    }

    public void roll() {
        for (int i = 0; i < dice.size(); i++) {
            dice.get(i).roll();
        }
    }
}
