package com.example.acrimin.dicegames;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class TenThousand extends AppCompatActivity {
    public Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_thousand);

        LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayoutTable);
        Context context = getApplicationContext();
        table = new Table(6, ll, context);
    }

    @Override
    protected void onPause() {
        super.onPause();
        table.save();
    }

    public void rollPress(View view) {
        table.roll();
    }
}
