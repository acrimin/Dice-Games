package com.example.acrimin.dicegames;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

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

    public Dice(Context context) {
        random = new Random();
        this.scale = context.getResources().getDisplayMetrics().density;
        this.select = false;

        imageView = new ImageView(context);
        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(
                getPixels(80),
                getPixels(80),
                1
        );
        imageView.setLayoutParams(layoutParams1);
        imageView.setPadding(
                getPixels(5), getPixels(5), getPixels(5), getPixels(5)
        );
        roll();
        imageView.setImageResource(getDrawable());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select = !select;
                imageView.setImageResource(getDrawable());
            }
        });
    }

    public Dice(Context context, int side) {
        this(context);
        this.side = side - 1;
        imageView.setImageResource(getDrawable());
    }

    public int getSide() {
        return side + 1;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void roll() {
        side = random.nextInt(6);
        imageView.setImageResource(getDrawable());
    }

    private int getPixels(int dp) {
        return (int) (dp * scale + 0.5f);
    }

    private int getDrawable() {
        if (select) {
            if (side == 0)
                return R.drawable.one_select;
            else if (side == 1)
                return R.drawable.two_select;
            else if (side == 2)
                return R.drawable.three_select;
            else if (side == 3)
                return R.drawable.four_select;
            else if (side == 4)
                return R.drawable.five_select;
            else
                return R.drawable.six_select;
        } else {
            if (side == 0)
                return R.drawable.one;
            else if (side == 1)
                return R.drawable.two;
            else if (side == 2)
                return R.drawable.three;
            else if (side == 3)
                return R.drawable.four;
            else if (side == 4)
                return R.drawable.five;
            else
                return R.drawable.six;
        }
    }
}
