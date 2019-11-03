package com.example.bodeng.boplayhouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ReactionActivity extends ScoreableGame {

    ConstraintLayout outerBorder;
    ConstraintLayout inner;
    boolean canScore = false;
    boolean gameOver = false;
    ReactionGame rg = new ReactionGame();
    TextView scoreBoard;
    //GameManager gm;
    int difficulty = 10;
    //int score = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canScore = false;
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_reaction_game);
        outerBorder = findViewById(R.id.outer);
        inner = findViewById(R.id.inner);
        scoreBoard = findViewById(R.id.scoreboard);
        gm = (GameManager) extras.getSerializable("GameState");
        outerBorder.setBackgroundColor(Color.CYAN);
        inner.setBackgroundColor(Color.WHITE);
        startRandomTimer();

    }

    private void startRandomTimer(){
        int rand = 1000 * (int)(Math.random() * 15 + 5);
        new CountDownTimer(rand, 1000) {
            @Override
            public void onTick(long l) {
                if (canScore || gameOver){
                    return;
                }
                int random = (int)(Math.random() * 100 + 1);
                if (random < 4 * difficulty){
                    generateDistraction();
                }
            }

            @Override
            public void onFinish() {
                canScore = true;
                inner.setBackgroundColor(Color.RED);
                rg.startTime();
            }
        }.start();
    }

    public void stopTimer(View view) {
        score = 0;
        gameOver = true;
        if (!canScore){
            scoreBoard.setText("Score: Failed");
            sendToScorePage();
        }
        else{
            score = Math.max((int)(1000 * (1- rg.stopTimer())),0);
            scoreBoard.setText("Score: " + score);
            sendToScorePage();
        }
    }

    public void generateDistraction(){
        int random = (int) (Math.random() * 5);
        if (random == 0){
            inner.setBackgroundColor(Color.BLUE);
        }
        if (random == 1){
            inner.setBackgroundColor(Color.YELLOW);
        }
        if (random == 2){
            inner.setBackgroundColor(Color.GREEN);
        }
        if (random == 3){
            inner.setBackgroundColor(Color.LTGRAY);
        }
        if (random == 4){
            inner.setBackgroundColor(Color.BLACK);
        }
    }

}
