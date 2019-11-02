package com.example.bodeng.boplayhouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class BopItActivity extends AppCompatActivity {

    BopItGame game;
    Button buttonTopLeft;
    Button buttonTopRight;
    Button buttonBottomLeft;
    Button buttonBottomRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bop_it);

        buttonTopLeft = (Button) findViewById(R.id.button_topleft);
        buttonTopRight = (Button) findViewById(R.id.button_topright);
        buttonBottomLeft = (Button) findViewById(R.id.button_bottomleft);
        buttonTopLeft = (Button) findViewById(R.id.button_bottomright);

        game = new BopItGame();
        displaySequence();
    }



    public void displaySequence() {
        ArrayList<String> sequence = game.correctSequence;

        for (String button : sequence) {
            if (button.equals("TL"))
                flashButton(buttonTopLeft, game.msFlash);
            if (button.equals("TR"))
                flashButton(buttonTopRight, game.msFlash);
            if (button.equals("BL"))
                flashButton(buttonBottomLeft, game.msFlash);
            if (button.equals("BR"))
                flashButton(buttonBottomRight, game.msFlash);
            android.os.SystemClock.sleep(game.msFlash);
        }
    }

    public void flashButton(Button button, int msFlash) {
        button.setAlpha(.5f);
        android.os.SystemClock.sleep(msFlash);
        button.setAlpha(0);
    }

    public void button_topleft_pressed(View view) {
        game.topLeftPressed();
    }

    public void button_topright_pressed(View view) {
        game.topRightPressed();
    }

    public void button_bottomLeft_pressed(View view) {
        game.bottomLeftPressed();
    }

    public void button_bottomRight_pressed(View view) {
        game.bottomRightPressed();
    }

}
