package com.example.bodeng.boplayhouse;

public class ReactionGame {

    double startTime;

    public ReactionGame(){

    }

    public void startTime(){
        startTime = System.currentTimeMillis();
    }

    public double stopTimer() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }
    public static String getInstructions(){
        return "Press the button as soon as the screen turns blue";
    }
}
