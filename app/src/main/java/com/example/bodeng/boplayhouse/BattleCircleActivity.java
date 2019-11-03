package com.example.bodeng.boplayhouse;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class BattleCircleActivity extends AppCompatActivity {

    private ProgressBar progress;
    private TextView timeUp;
    private TextView correctWords;
    private TextView scoreText;
    private EditText userEntry;
    private Handler handler;

    private StringBuilder sb;

    private BattleCircleGame game;

    private int progressStatus;

    private static final int SECONDS = 20;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final int MILLIS_PER_TICK = 50;
    private static final int NUM_TICKS = SECONDS * MILLIS_PER_SECOND / MILLIS_PER_TICK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_circle_game);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        timeUp = (TextView) findViewById(R.id.timeUpTextView);
        userEntry = (EditText) findViewById(R.id.wordInputEditText);
        correctWords = (TextView) findViewById(R.id.correctWordsTextView);

        correctWords.setMovementMethod(new ScrollingMovementMethod());

        scoreText = (TextView) findViewById(R.id.Score);
        sb = new StringBuilder();

        try {
            game = new BattleCircleGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        userEntry.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    String userWord = userEntry.getText().toString();
                    int code = game.tryWord(userWord);
                    if(code == 0 || code == 1) {
                        correctWords.setText(correctWords.getText() + "\n" + userWord);
                        if(code == 0) {
                            // TODO: Write code to make the user lose here
                        } else {
                            scoreText.setText("SCORE: " + game.getScore());
                        }

                    }
                    userEntry.setText("");
                    return true;
                }
                return false;
            }
        });

        handler = new Handler();

        progress.setProgress(0);
        progress.setMax(NUM_TICKS); // This is the total number of ticks we want
        timeUp.setVisibility(View.INVISIBLE);
        scoreText.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < NUM_TICKS) {
                    progressStatus++;
                    android.os.SystemClock.sleep(MILLIS_PER_TICK);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progress.setProgress(progressStatus);
                        }
                    });
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        timeUp.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();

//        // While the time is not up yet
//        while(timeUp.getVisibility() == View.INVISIBLE) {
//
//
//        }

//        timer = new CountDownTimer(20000, 10) {
//            @Override
//            public void onTick(long l) {
//                progress.incrementProgressBy(1);
//            }
//
//            @Override
//            public void onFinish() {
//                progress.setBackgroundColor(100);
//            }
//        }.start();

//        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.textView);
    }

    public void inputWord(View view) {
        String userWord = userEntry.getText().toString();
        int code = game.tryWord(userWord);
        if(code == 0 || code == 1) {
            sb.append(userWord + "\n");
            correctWords.setText(sb.toString());
            if(code == 0) {
                // TODO: Write code to make the user lose here
            } else {

            }

        }
        userEntry.setText("");
    }

}
