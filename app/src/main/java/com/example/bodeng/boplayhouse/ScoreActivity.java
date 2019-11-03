package com.example.bodeng.boplayhouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class ScoreActivity extends AppCompatActivity {
    TextView t;
    ConstraintLayout background;
    GameManager gm;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        t = (TextView)(findViewById(R.id.textView19));
        gm = (GameManager) extras.getSerializable("GameState");
        background = findViewById(R.id.scoreBG);
        background.setBackgroundColor(Color.rgb(255,102,0));
        score = extras.getInt("score");
        t.setTextColor(Color.WHITE);
        t.setText("You Scored " + score + " Points!");
    }

    public void nextButtonPressed(View view) {
        gm.advanceTurn();
        Intent game = new Intent(this, com.example.bodeng.boplayhouse.InstructionsViewer.class);
        game.putExtra("GameState", gm);
        startActivity(game);
    }
}
