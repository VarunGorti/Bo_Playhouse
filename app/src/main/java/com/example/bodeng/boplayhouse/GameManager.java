package com.example.bodeng.boplayhouse;

import java.io.Serializable;

public class GameManager implements Serializable {

    private int[] scores;
    private String[] playerNames;
    private int currentPlayer;

    public GameManager(int numPlayers){
        scores = new int[numPlayers];
        playerNames = new String[numPlayers];
        currentPlayer = 0;
    }


    public void updateScore(int increment){
        scores[currentPlayer]+=increment;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public void advanceTurn(){
        currentPlayer = (currentPlayer + 1) % playerNames.length;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
