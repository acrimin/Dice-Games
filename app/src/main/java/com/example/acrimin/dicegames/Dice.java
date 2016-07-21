package com.example.acrimin.dicegames;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * Created by acrimin on 7/18/2016.
 */
public class Dice {
    private int side;
    private ImageView imageView;
    private float scale;
    private Random random;
    private boolean select;
    private Context context;

    private static final String[] diceName = {
            "one",
            "two",
            "three",
            "four",
            "five",
            "six"};


    /* init with set side and possible nonselection */
    public Dice(Context context, int side, boolean sent) {
        random = new Random();

        this.context = context;
        this.scale = context.getResources().getDisplayMetrics().density;
        this.select = false;
        this.side = side;

        if (sent)
            setSentDice();
        else
            setTableDice();
    }

    /* init with set side and selection */
    public Dice(Context context, int side) {
        this(context, side, false);
    }

    /* Default init with selection */
    public Dice(Context context) {
        this(context, new Random().nextInt(6), false);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void roll() {
        side = random.nextInt(6);
        updateDrawable();
    }

    public int getSide() {
        return side;
    }

    public boolean isSelect() {
        return select;
    }

    private void setTableDice() {
        float scale = context.getResources().getDisplayMetrics().density;

        imageView = new ImageView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                getPixels(80),
                getPixels(80),
                1
        );
        imageView.setLayoutParams(layoutParams);
        imageView.setPadding(5, 5, 5, 5);
        imageView.setImageResource(getDrawable());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select = !select;
                updateDrawable();
            }
        });
    }

    public void setSentDice() {
        select = false;
        imageView = new ImageView(context);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
        );

        imageView.setLayoutParams(layoutParams);
        imageView.setPadding(5, 5, 5, 5);
        imageView.setImageResource(getDrawable());
    }

    private void updateDrawable() {
        imageView.setImageResource(getDrawable());
    }

    private int getDrawable() {
        String name = diceName[side];
        if (select)
            name += "_select";

        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    private int getPixels(int dp) {
        return (int) (dp * scale + 0.5f);
    }
}
