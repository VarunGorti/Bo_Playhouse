package com.example.bodeng.boplayhouse;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class BopItActivity extends AppCompatActivity {

    BopItGame game;
    Button buttonTopLeft;
    Button buttonTopRight;
    Button buttonBottomLeft;
    Button buttonBottomRight;
    TextView gameOverText;

    private ProgressBar progressBar;
    private Handler handler;
    int progressStatus;

    private static final int SECONDS = 20;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final int MILLIS_PER_TICK = 50;
    private static final int NUM_TICKS = SECONDS * MILLIS_PER_SECOND / MILLIS_PER_TICK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bop_it);

        buttonTopLeft = (Button) findViewById(R.id.button_topleft);
        buttonTopRight = (Button) findViewById(R.id.button_topright);
        buttonBottomLeft = (Button) findViewById(R.id.button_bottomleft);
        buttonBottomRight = (Button) findViewById(R.id.button_bottomright);

        gameOverText = (TextView) findViewById(R.id.gameOverText);
        gameOverText.setVisibility(View.INVISIBLE);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        handler = new Handler();
        progressBar.setMax(NUM_TICKS);
        progressStatus = 0;


        game = new BopItGame(5, 5);

        // exit, go to next player

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (game.isPassedRound()) {
                    game.addRandomButton();

                    android.os.SystemClock.sleep(1000);
                    displaySequence();
                    game.setRoundRunning(true);

                    Thread timer = new Thread() {
                        @Override
                        public void run() {
                            progressStatus = 0;
                            while (!this.isInterrupted() && progressStatus < NUM_TICKS) {
                                progressStatus++;
                                android.os.SystemClock.sleep(MILLIS_PER_TICK);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                    }
                                });
                            }
                            if (game.isRoundRunning()) {
                                game.setPassedRound(false);
                                game.setRoundRunning(false);
                            }

                        }
                    };
                    timer.start();

                    while (game.isRoundRunning()) {}
                    timer.interrupt();

                    progressStatus = 0;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });

                    if (game.isPassedRound()) {
                        game.setRoundsPassed(game.getRoundsPassed() + 1);
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameOverText.setVisibility(View.VISIBLE);
                        gameOverText.setText("Score: " + game.getRoundsPassed());
                    }
                });

            }
        }).start();


    }





    public void displaySequence() {
        ArrayList<String> sequence = game.correctSequence;

        for (String button : sequence) {
            if (button.equals("TL"))
                flashButton(buttonTopLeft, game.getMsFlash());
            if (button.equals("TR"))
                flashButton(buttonTopRight, game.getMsFlash());
            if (button.equals("BL"))
                flashButton(buttonBottomLeft, game.getMsFlash());
            if (button.equals("BR"))
                flashButton(buttonBottomRight, game.getMsFlash());
            android.os.SystemClock.sleep(game.getMsFlash());
        }
    }


    public void flashButton(Button button, int msFlash) {
        button.setAlpha(.5f);
        android.os.SystemClock.sleep(msFlash);
        button.setAlpha(1f);
    }

    public void button_topleft_pressed(View view) {
        flashButton(buttonTopLeft, 200);
        game.topLeftPressed();
    }

    public void button_topright_pressed(View view) {
        flashButton(buttonTopRight, 200);
        game.topRightPressed();
    }

    public void button_bottomLeft_pressed(View view) {
        game.bottomLeftPressed();
        flashButton(buttonBottomLeft, 250);
    }

    public void button_bottomRight_pressed(View view) {
        game.bottomRightPressed();
        flashButton(buttonBottomRight, 250);
    }

}
