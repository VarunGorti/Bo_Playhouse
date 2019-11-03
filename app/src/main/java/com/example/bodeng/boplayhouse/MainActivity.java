package com.example.bodeng.boplayhouse;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private TextView titleText;
    private ConstraintLayout titleFrame;
    private Spinner playerSpinner;
    private TextView numPlayView;
    private ConstraintLayout numPlaysFrame;
    private GameManager gm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleText = (TextView)findViewById(R.id.titleFrame);
        titleText.setTextColor(Color.RED);
        titleFrame = (ConstraintLayout)(findViewById(R.id.titleBox));
        numPlaysFrame = (ConstraintLayout)(findViewById(R.id.numPlaysLayout));
        titleFrame.setBackgroundColor(Color.CYAN);
        numPlaysFrame.setBackgroundColor(Color.CYAN);
        numPlayView = findViewById(R.id.numPlayView);
        playerSpinner = (Spinner)(findViewById(R.id.playerCountSpinner));
        numPlayView.setText("Select Number of Players");
        numPlayView.setTextColor(Color.RED);
        String[] playerOptions = {"1","2","3","4","5","6"};
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, playerOptions);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerSpinner.setAdapter(adp1);


    }

    public void onClick(View view) {
        Intent nameEntry = new Intent(this, com.example.bodeng.boplayhouse.EnterNamesActivity.class);
        nameEntry.putExtra("NumPlays", playerSpinner.getSelectedItem().toString());
        gm = new GameManager(Integer.parseInt(playerSpinner.getSelectedItem().toString()));
        nameEntry.putExtra("GameState", gm);
        startActivity(nameEntry);
    }
}
