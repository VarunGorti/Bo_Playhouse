package com.example.bodeng.boplayhouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InstructionsViewer extends AppCompatActivity {

    ConstraintLayout instructions;
    ConstraintLayout title;
    GameManager gm;
    TextView turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_instructions);
        instructions = (ConstraintLayout)(findViewById(R.id.instructions));
        title = (ConstraintLayout)(findViewById(R.id.topBanner));
        title.setBackgroundColor(Color.CYAN);
        instructions.setBackgroundColor(Color.CYAN);
        TextView[] pNames = {findViewById(R.id.p1Name), findViewById(R.id.p2Name), findViewById(R.id.p3Name), findViewById(R.id.p4Name)};
        TextView[] pScores = {findViewById(R.id.p1Score), findViewById(R.id.p2Score), findViewById(R.id.p3Score), findViewById(R.id.p4Score)};
        gm = (GameManager) extras.getSerializable("GameState");
        turn = (TextView)(findViewById(R.id.turnLister));
        String[] names = gm.getPlayerNames();
        turn.setText(names[gm.getCurrentPlayer()] + "'s Turn");
        int[] scores = gm.getScores();
        for (int i = 0; i < pNames.length; i++){
            if (i >= names.length){
                pNames[i].setVisibility(View.INVISIBLE);
                pScores[i].setVisibility(View.INVISIBLE);
            }
            else{
                pNames[i].setText(names[i]);
                pScores[i].setText(scores[i] + "");
            }
        }
    }

    public void startGame(View view) {
        Intent game = new Intent(this, com.example.bodeng.boplayhouse.WordFinderActivity.class);
        game.putExtra("GameState", gm);
        startActivity(game);
    }
}
