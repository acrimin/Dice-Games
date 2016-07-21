package com.example.acrimin.dicegames;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by acrimin on 7/20/2016.
 */
public class GameManager {
    private int gameType;
    private int playerTurn;
    private ArrayList<Dice> tableDice;
    private ArrayList<Dice> sentDice;
    private int[] scores;
    private Context context;

    /* Create New Game */
    public GameManager(int game, int players, Context context) {
        this.context = context;
        this.gameType = game;
        tableDice = new ArrayList<>();
        sentDice = new ArrayList<>();
        scores = new int[players];

        for (int i = 0; i < players; i++) {
            scores[i] = 0;
        }
        playerTurn = 0;

        for (int i = 0; i < getDiceCount(); i++) {
            tableDice.add(new Dice(context));
        }
    }

    public Dice getTableDice(int index) {
        return tableDice.get(index);
    }

    public int getTableDiceCount() {
        return tableDice.size();
    }

    public void roll() {
        for (Dice d : tableDice)
            d.roll();
    }

    public void send() {
        ArrayList<Dice> send = new ArrayList<>();
        for (Dice d : tableDice) {
            if (d.isSelect()) {
                send.add(d);
            }
        }

        if (sendCheck(send)) {
            for (Dice d : send) {
                tableDice.remove(d);
                d.setSentDice();
                sentDice.add(d);
            }
        }


    }

    private boolean sendCheck(ArrayList<Dice> send) {
        return true;
    }

    private int getDiceCount() {
        return 6;
    }


}
