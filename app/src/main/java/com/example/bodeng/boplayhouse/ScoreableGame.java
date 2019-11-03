package com.example.bodeng.boplayhouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public abstract class ScoreableGame extends AppCompatActivity {

    GameManager gm;
    int score;

    public void sendToScorePage(){
        Intent game = new Intent(this, com.example.bodeng.boplayhouse.ScoreActivity.class);
        gm.updateScore(score);
        game.putExtra("GameState", gm);
        game.putExtra("score", score);
        startActivity(game);
    }
}
