package com.example.bodeng.boplayhouse;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class WordFinderActivity extends ScoreableGame {

    private HashSet<String> dictionary;
    private int[] frequencyTable;
    private char[] easyLetters = {'E','T','A','O','I','N','S','R','H'};
    private char[] medLetters = {'L','D','C','U','M','F','P','G','W'};
    private char[] hardLetters = {'Y','B','V','K','X','J','Q','Z'};
    private TextView letterBank;
    private ConstraintLayout display;
    private ConstraintLayout backdrop;
    private TextView bottomBox;
    private EditText entryBar;
    //A = 65
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_finder);
        dictionary = new HashSet<>();
        loadDictionary();
        frequencyTable = new int[26];
        Bundle extras = getIntent().getExtras();
        gm = (GameManager) extras.getSerializable("GameState");
        letterBank = (TextView)(findViewById(R.id.wordBank));
        entryBar = (EditText)(findViewById(R.id.wordEnter));
        display = findViewById(R.id.constraintLayout3);
        backdrop = findViewById(R.id.constraintLayout4);
        bottomBox = findViewById(R.id.wordBank);
        display.setBackgroundColor(Color.rgb(255,102,0));
        bottomBox.setBackgroundColor(Color.WHITE);
        letterBank.setBackgroundColor(Color.WHITE);
        backdrop.setBackgroundColor(Color.CYAN);
        generateRandomLetters(10);
    }


    private void generateRandomLetters(int difficulty){
        int numLets = 16 - difficulty;
        String lbText = "";
        for (int i = 0; i < numLets; i++){
            int random = (int)(Math.random() * 100 + 1);
            char c = 'A';
            if (random < 5 + 3 * difficulty){
                c = hardLetters[(int) (hardLetters.length * Math.random())];
                frequencyTable[c - 65]++;
            }
            else if (random < 35 + 3 * difficulty){
                c = medLetters[(int) (medLetters.length * Math.random())];
                frequencyTable[c - 65]++;
            }
            else{
                c = easyLetters[(int) (easyLetters.length * Math.random())];
                frequencyTable[c - 65]++;
            }
            lbText += c;
            if (i != numLets - 1){
                lbText += " ";
            }
        }
        letterBank.setText(lbText);
    }
    private void loadDictionary(){
        try {
            Resources res = getResources();
            InputStream inputStream = res.openRawResource(R.raw.dictionary);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null){
                dictionary.add(line.toUpperCase());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterWord(View view) {
        String word = entryBar.getText().toString().toUpperCase();
        int[] wordFreq = new int[26];
        for (int i = 0; i < word.toCharArray().length; i++){
            wordFreq[word.charAt(i)-65]++;
        }
        if (!dictionary.contains(word)){
            return;
        }
        for (int i = 0; i < 26; i++){
            if (wordFreq[i] > frequencyTable[i]){
                return;
            }
        }
        score = (100 * word.length());
        sendToScorePage();
    }
}
