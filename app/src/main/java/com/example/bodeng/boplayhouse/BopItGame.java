package com.example.bodeng.boplayhouse;

import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by bodeng on 11/2/19.
 */

public class BopItGame {

    ArrayList<String> correctSequence;
    int currIndex;
    int difficulty;
    int msFlash;
    int roundsPassed;

    public BopItGame() {
        new BopItGame(5, 5);
    }

    public BopItGame(int difficulty, int defaultSequenceLength) {
        this.difficulty = difficulty;
        correctSequence = new ArrayList<>();
        msFlash = 500 - (difficulty * 40);
        currIndex = 0;
        roundsPassed = 0;

        // generate the first sequence
        for (int i = 0; i < defaultSequenceLength; i++) {
            addRandomButton();
        }
    }

    public void addRandomButton() {

        double rand = Math.random();
        if (rand < .25) {
            correctSequence.add("TL");
        } else if (rand < .5) {
            correctSequence.add("TR");
        } else if (rand < .75) {
            correctSequence.add("BL");
        } else {
            correctSequence.add("BR");
        }
    }

    public boolean topLeftPressed() {
        if (correctSequence.get(currIndex).equals("TL")) {
            currIndex++;
            if (currIndex >= correctSequence.size()) {
                currIndex = 0;
                addRandomButton();


            }
            return true;
        } else {
            // end game
            return false;
        }
    }

    public boolean topRightPressed() {
        if (correctSequence.get(currIndex).equals("TR")) {
            currIndex++;
        } else {
            // end game
            return false;
        }
    }

    public boolean bottomLeftPressed() {
        if (correctSequence.get(currIndex).equals("BL")) {
            currIndex++;
        } else {
            // end game
            return false;
        }
    }

    public boolean bottomRightPressed() {
        if (correctSequence.get(currIndex).equals("BR")) {
            currIndex++;
        } else {
            // end game
            return false;
        }
    }

    public String getInstructions() {
        return "A random sequence will be displayed. Press the buttons in the same order to pass" +
                " the round. The player who clears the most rounds wins.";
    }

}
